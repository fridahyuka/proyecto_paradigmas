package mx.uamex.fi.paradigmas.model;

import mx.uamex.fi.paradigmas.data.Record;
import java.util.ArrayList;

public interface RecordsDAO {
    void insertar(Record record);
    ArrayList<Record> consultar();
    void actualizar(Record record);
    void borrar(Record record);
}
