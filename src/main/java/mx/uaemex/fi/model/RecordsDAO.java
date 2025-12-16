package mx.uaemex.fi.model;

import mx.uaemex.fi.model.data.Record;
import java.util.ArrayList;

public interface RecordsDAO {
    void insertar(Record record);

    ArrayList<Record> consultar();

    void actualizar(Record record);

    void borrar(Record record);
}
