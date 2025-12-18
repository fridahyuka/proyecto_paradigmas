package mx.uaemex.fi.API;

import mx.uaemex.fi.API.responses.APIPlayerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.util.List;
import java.net.http.HttpRequest;

public class PPTAPIPlayersClient extends PPTAPIClient {

    private final ObjectMapper objectMapper;

    public PPTAPIPlayersClient() {
        super();
        this.initializeHttpClient();
        this.setBaseURL(super.getBaseURL() + "api/users/player/");
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    public List<APIPlayerResponse> getAllPlayers() throws Exception {
        HttpRequest request = buildGetRequest("")
                .GET()
                .build();

        String responseBody = executeRequest(request);

        CollectionType listType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, APIPlayerResponse.class);
        return objectMapper.readValue(responseBody, listType);
    }

    public APIPlayerResponse getPlayerById(int id) throws Exception {
        HttpRequest request = buildGetRequest(id + "/")
                .GET()
                .build();

        String responseBody = executeRequest(request);
        return objectMapper.readValue(responseBody, APIPlayerResponse.class);
    }

    public List<APIPlayerResponse> getPlayersByUsername(String username) throws Exception {
        String encodedUsername = java.net.URLEncoder.encode(username, "UTF-8");
        String endpoint = "?search=" + encodedUsername;

        HttpRequest request = buildGetRequest(endpoint)
                .GET()
                .build();

        String responseBody = executeRequest(request);

        CollectionType listType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, APIPlayerResponse.class);
        return objectMapper.readValue(responseBody, listType);
    }

    public static void main(String[] args) {
        try {
            PPTAPIPlayersClient client = new PPTAPIPlayersClient();

            // Obtener todos los jugadores
            List<APIPlayerResponse> allRecords = client.getAllPlayers();
            System.out.println("Total jugadores: " + allRecords.size());

            for (APIPlayerResponse apiRecordsResponse : allRecords) {

                System.out.println(apiRecordsResponse);

            } // Obtener todos los jugadores gaelglz
            List<APIPlayerResponse> gaelglzRecords = client.getPlayersByUsername("gaelglz");
            System.out.println("Total jugadores gaelglz: " + gaelglzRecords.size());

            for (APIPlayerResponse apiRecordsResponse : gaelglzRecords) {

                System.out.println(apiRecordsResponse);

            }

            // AHora para el primer jugador

            APIPlayerResponse first = client.getPlayerById(1);
            System.out.println("Primer jugador (id=1)");
            System.out.println(first);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}