package mx.uaemex.fi.API;

import java.net.http.HttpClient;

public abstract class PPTAPIClient<T, P> {

    private String baseURL = "https://rps-server-production-4251.up.railway.app/";

    private HttpClient client;

    abstract T consult(P params);

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

}
