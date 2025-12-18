package mx.uaemex.fi.service;

import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.model.data.Record;

import java.util.ArrayList;

public interface RecordsService {
    public void insertar(Record record);

    public ArrayList<Record> consultar(Jugador j);

    public void actualizar(Record record);

    public void borrar(Record record);

}
