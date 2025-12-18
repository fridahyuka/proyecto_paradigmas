package mx.uaemex.fi.API;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.Map;

public abstract class PPTAPIClient {

    protected String baseURL = "https://rps-server-production-4251.up.railway.app/";
    protected HttpClient client;

    protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    protected static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(5);

    public PPTAPIClient() {

    }

    protected void initializeHttpClient() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .build();
    }

    protected HttpRequest.Builder buildGetRequest(String endpoint) {
        return HttpRequest.newBuilder()
                .uri(java.net.URI.create(baseURL + endpoint))
                .timeout(DEFAULT_TIMEOUT)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("User-Agent", "PPT-API-Client/1.0");
    }

    protected HttpRequest.Builder buildAuthenticatedGetRequest(
            String endpoint,
            String username,
            String password) {

        String authHeader = "Basic " + Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes());

        return buildGetRequest(endpoint)
                .header("Authorization", authHeader);
    }

    protected String executeRequest(HttpRequest request) throws Exception {
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new RuntimeException(
                    "HTTP Request failed with status code: " + response.statusCode());
        }
    }

    protected HttpRequest.Builder withCustomHeaders(
            HttpRequest.Builder builder,
            Map<String, String> headers) {

        headers.forEach(builder::header);
        return builder;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public static Duration getDefaultTimeout() {
        return DEFAULT_TIMEOUT;
    }

    public static Duration getDefaultConnectTimeout() {
        return DEFAULT_CONNECT_TIMEOUT;
    }

}
