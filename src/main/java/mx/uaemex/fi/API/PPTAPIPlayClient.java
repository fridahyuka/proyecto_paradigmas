package mx.uaemex.fi.API;

import mx.uaemex.fi.API.responses.APIPlayResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpRequest;
import mx.uaemex.fi.game.Movimiento;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PPTAPIPlayClient extends PPTAPIClient {

    private final ObjectMapper objectMapper;

    public PPTAPIPlayClient() {
        super();
        this.initializeHttpClient();
        this.setBaseURL(super.getBaseURL() + "api/games/play/");
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    public APIPlayResponse play(String username, String password, Movimiento playerChoice) throws Exception {
        String formData = "player_choice="
                + URLEncoder.encode(playerChoice.getSymbol(), StandardCharsets.UTF_8.toString());

        HttpRequest request = buildAuthenticatedPostRequest("", username, password, formData)
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        String responseBody = executeRequest(request);
        return objectMapper.readValue(responseBody, APIPlayResponse.class);
    }

    protected HttpRequest.Builder buildAuthenticatedPostRequest(
            String endpoint,
            String username,
            String password,
            String formData) {

        String authHeader = "Basic " + Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes());

        return HttpRequest.newBuilder()
                .uri(java.net.URI.create(baseURL + endpoint))
                .timeout(DEFAULT_TIMEOUT)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .header("User-Agent", "PPT-API-Client/1.0")
                .header("Authorization", authHeader);
    }

    public static void main(String[] args) {
        try {
            PPTAPIPlayClient client = new PPTAPIPlayClient();

            // Credenciales de prueba (reemplazar con credenciales reales)
            String testUsername = "tester";
            String testPassword = "tester123";

            System.out.println("Jugando");

            for (int i = 0; i < 5; i++) {
                APIPlayResponse response = client.play(testUsername, testPassword, Movimiento.PIEDRA);
                System.out.println(response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}