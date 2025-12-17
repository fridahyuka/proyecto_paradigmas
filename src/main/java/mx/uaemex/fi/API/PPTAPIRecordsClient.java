package mx.uaemex.fi.API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mx.uaemex.fi.model.data.Record;

// TODO Separar o implementar el record del contrato de la API con el ya presente
// TODO Implementar las dos consultas restantes

public class PPTAPIRecordsClient extends PPTAPIClient<List<Record>, String> {

    public PPTAPIRecordsClient() {
        this.setBaseURL(getBaseURL() + "api/games/records/");
    }

    @Override
    public List<Record> consult(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Record> consult(int id) {
        return null;
    }

    public List<Record> consult() {
        List<Record> records = new ArrayList<>();
        JSONParser parser = new JSONParser();

        this.setClient(HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.getBaseURL()))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .header("User-Agent", "Java HttpClient")
                .GET()
                .build();

        try {
            HttpResponse<String> response = this.getClient()
                    .send(
                            request,
                            HttpResponse.BodyHandlers.ofString());

            // ! Aquí va lo rico
            Object obj = parser.parse(response.body());

            JSONArray jsonArray = (JSONArray) obj;

            for (Object item : jsonArray) {
                if (item instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) item;
                    Record record = createRecordFromJson(jsonObject);
                    records.add(record);
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return records;
    }

    public static Record createRecordFromJson(JSONObject jsonObject) throws ParseException {

        System.out.println("Convirtiendo elemento");
        Record record = new Record();
        if (jsonObject.containsKey("id")) {
            Long longId = (Long) jsonObject.get("id");
            record.setId(longId.intValue());
        }
        if (jsonObject.containsKey("record")) {
            Long longRecord = (Long) jsonObject.get("record");
            record.setRecord(longRecord.intValue());
        }
        if (jsonObject.containsKey("updated_at")) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse((String) jsonObject.get("updated_at"), formatter);
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            record.setFecha(date);
        }

        return record;
    }

    public static void main(String[] args) {
        PPTAPIRecordsClient client = new PPTAPIRecordsClient();
        for (Record r : client.consult()) {

            System.out.println(r);

        }
    }

}
