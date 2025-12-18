package mx.uaemex.fi.API;

import mx.uaemex.fi.API.responses.APIRegisterResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.http.HttpRequest;

public class PPTAPIRegisterClient extends PPTAPIClient {

    private final ObjectMapper objectMapper;

    public PPTAPIRegisterClient() {
        super();
        this.initializeHttpClient();
        this.setBaseURL(super.getBaseURL() + "api/users/register/");
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    public APIRegisterResponse register(String username, String password) throws Exception {

        String requestBody = String.format(
                "{\"username\": \"%s\", \"password\": \"%s\"}",
                username, password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(this.baseURL))
                .timeout(DEFAULT_TIMEOUT)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("User-Agent", "PPT-API-Client/1.0")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        String responseBody = executeRequestUnsafely(request);

        JsonNode jsonNode = objectMapper.readTree(responseBody);

        APIRegisterResponse response = new APIRegisterResponse();

        if (jsonNode.has("username")) {
            JsonNode usernameNode = jsonNode.get("username");
            if (usernameNode.isTextual()) {

                response.setUsername(new String[] { usernameNode.asText() });
            } else if (usernameNode.isArray()) {

                String[] usernameArray = new String[usernameNode.size()];
                for (int i = 0; i < usernameNode.size(); i++) {
                    usernameArray[i] = usernameNode.get(i).asText();
                }
                response.setUsername(usernameArray);
            }
        }

        if (jsonNode.has("password")) {
            JsonNode passwordNode = jsonNode.get("password");
            if (passwordNode.isTextual()) {

                response.setPassword(new String[] { passwordNode.asText() });
            } else if (passwordNode.isArray()) {

                String[] passwordArray = new String[passwordNode.size()];
                for (int i = 0; i < passwordNode.size(); i++) {
                    passwordArray[i] = passwordNode.get(i).asText();
                }
                response.setPassword(passwordArray);
            }
        }

        return response;
    }

    public boolean isRegistrationSuccessful(APIRegisterResponse response) {
        if (response == null || response.getUsername() == null || response.getUsername().length == 0) {
            return false;
        }

        String firstUsername = response.getUsername()[0];
        return !isErrorMessage(firstUsername);
    }

    private boolean isErrorMessage(String text) {
        if (text == null)
            return false;

        String lowerText = text.toLowerCase();
        return lowerText.contains("already exists") ||
                lowerText.contains("ensure this field") ||
                lowerText.contains("required") ||
                lowerText.contains("invalid") ||
                lowerText.contains("error");
    }

    public static void main(String[] args) {
        try {
            PPTAPIRegisterClient client = new PPTAPIRegisterClient();

            String username = "tester";
            String password = "tester123";

            System.out.println("Registrando usuario: " + username);
            APIRegisterResponse response = client.register(username, password);
            System.out.println("Respuesta: " + response);
            System.out.println("¿Registro exitoso? " + client.isRegistrationSuccessful(response));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}