package mx.uaemex.fi.API;

import mx.uaemex.fi.API.responses.APIWhoamiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpRequest;

public class PPTAPIWhoamiClient extends PPTAPIClient {

    private final ObjectMapper objectMapper;

    public PPTAPIWhoamiClient() {
        super();
        this.initializeHttpClient();
        this.setBaseURL(super.getBaseURL() + "api/users/whoami/");
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    public APIWhoamiResponse getWhoami(String username, String password) throws Exception {
        HttpRequest request = buildAuthenticatedGetRequest("", username, password)
                .GET()
                .build();

        String responseBody = executeRequestUnsafely(request);
        return objectMapper.readValue(responseBody, APIWhoamiResponse.class);
    }

    public boolean testAuthentication(String username, String password) {
        try {
            APIWhoamiResponse response = getWhoami(username, password);
            return response != null && response.getUsername() != null;
        } catch (Exception e) {
            System.err.println("Error de autenticación: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            PPTAPIWhoamiClient client = new PPTAPIWhoamiClient();

            String testUsername = "tester";
            String testPassword = "tester123";

            System.out.println("Probando autenticación con whoami");

            if (client.testAuthentication(testUsername, testPassword)) {
                System.out.println("Autenticación exitosa");
            } else {
                System.out.println("Autenticación fallida");
                System.out.println("Por favor, verifica tus credenciales:");
            }

            APIWhoamiResponse whoami = client.getWhoami(testUsername, testPassword);
            System.out.println("Información del usuario:");
            System.out.println(whoami);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}