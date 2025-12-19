package mx.uaemex.fi.service;

import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.model.data.Record;

import java.util.List;

public interface RecordsService {
     void insertar(Record record);

     List<Record> consultar(Jugador j);

     Record consultarMax(Jugador j);

     void actualizar(Record record);

     void borrar(Record record);

}
