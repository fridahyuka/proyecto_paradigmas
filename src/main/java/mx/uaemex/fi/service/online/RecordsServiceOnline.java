package mx.uaemex.fi.service.online;

import java.util.ArrayList;
import java.util.List;

import mx.uaemex.fi.API.PPTAPIRecordsClient;
import mx.uaemex.fi.API.responses.APIRecordsResponse;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.model.data.Record;
import mx.uaemex.fi.service.RecordsService;

public class RecordsServiceOnline implements RecordsService {

    PPTAPIRecordsClient client;

    public RecordsServiceOnline() {
        this.client = new PPTAPIRecordsClient();
    }

    @Override
    public void actualizar(Record record) {
        return;
    }

    @Override
    public void borrar(Record record) {
        return;
    }

    @Override
    public ArrayList<Record> consultar(Jugador j) {

        try {
            List<APIRecordsResponse> recordsResponse = client.getRecordsByUsername(j.getLogin());
            ArrayList<Record> records = new ArrayList<>();

            for (APIRecordsResponse recordResponse : recordsResponse) {
                records.add(recordResponse.asRecord());
            }

            return records;

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Record consultarMax(Jugador j) {
        ArrayList<Record> records = this.consultar(j);

        Record maxRecord = new Record();
        maxRecord.setRecord(0);

        for (Record record : records) {
            if (record.getRecord() > maxRecord.getRecord()) {
                maxRecord = record;
            }
        }

        return maxRecord;
    }

    @Override
    public void insertar(Record record) {
        return;

    }

    public PPTAPIRecordsClient getClient() {
        return client;
    }

    public void setClient(PPTAPIRecordsClient client) {
        this.client = client;
    }

}
