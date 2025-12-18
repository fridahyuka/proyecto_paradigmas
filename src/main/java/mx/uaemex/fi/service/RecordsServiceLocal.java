package mx.uaemex.fi.service;

import mx.uaemex.fi.model.RecordsDAO;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.model.data.Record;

import java.sql.SQLException;
import java.util.ArrayList;

public class RecordsServiceLocal implements RecordsService {

    private RecordsDAO dao;

    public RecordsServiceLocal() {
    }

    public void setDao(RecordsDAO dao) {
        this.dao = dao;
    }

    @Override
    public void insertar(Record record) {
        dao.insertar(record);
    }

    @Override
    public ArrayList<Record> consultar(Jugador j) {
        return dao.consultar(j);
    }

    public Record consultarMax(Jugador j) {
        return dao.consultarMax(j);
    }

    @Override
    public void actualizar(Record record) {
        dao.actualizar(record);
    }

    @Override
    public void borrar(Record record) {
        dao.borrar(record);
    }
}
