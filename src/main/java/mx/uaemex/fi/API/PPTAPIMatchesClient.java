package mx.uaemex.fi.API;

import mx.uaemex.fi.API.responses.APIMatchesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.util.List;
import java.net.http.HttpRequest;

public class PPTAPIMatchesClient extends PPTAPIClient {

    private final ObjectMapper objectMapper;

    public PPTAPIMatchesClient() {
        super();
        this.initializeHttpClient();
        this.setBaseURL(super.getBaseURL() + "api/games/matches/");
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    public List<APIMatchesResponse> getAllMatches() throws Exception {
        HttpRequest request = buildGetRequest("")
                .GET()
                .build();

        String responseBody = executeRequest(request);

        CollectionType listType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, APIMatchesResponse.class);
        return objectMapper.readValue(responseBody, listType);
    }

    public APIMatchesResponse getMatchById(int id) throws Exception {
        HttpRequest request = buildGetRequest(id + "/")
                .GET()
                .build();

        String responseBody = executeRequest(request);
        return objectMapper.readValue(responseBody, APIMatchesResponse.class);
    }

    public List<APIMatchesResponse> getMatchesByUsername(String username) throws Exception {
        String encodedUsername = java.net.URLEncoder.encode(username, "UTF-8");
        String endpoint = "?search=" + encodedUsername;

        HttpRequest request = buildGetRequest(endpoint)
                .GET()
                .build();

        String responseBody = executeRequest(request);

        CollectionType listType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, APIMatchesResponse.class);
        return objectMapper.readValue(responseBody, listType);
    }

    public static void main(String[] args) {
        try {
            PPTAPIMatchesClient client = new PPTAPIMatchesClient();

            // Obtener todas las partidas
            List<APIMatchesResponse> allRecords = client.getAllMatches();
            System.out.println("Total partidas: " + allRecords.size());

            for (APIMatchesResponse apiRecordsResponse : allRecords) {

                System.out.println(apiRecordsResponse);

            } // Obtener todas las partidas de gaelglz
            List<APIMatchesResponse> gaelglzRecords = client.getMatchesByUsername("gaelglz");
            System.out.println("Total partidas de gaelglz: " + gaelglzRecords.size());

            for (APIMatchesResponse apiRecordsResponse : gaelglzRecords) {

                System.out.println(apiRecordsResponse);

            }

            // AHora para la primer partida

            APIMatchesResponse first = client.getMatchById(1);
            System.out.println("Primer partida (id=1)");
            System.out.println(first);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}