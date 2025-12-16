package mx.uaemex.fi.controller;

import mx.uaemex.fi.game.modo.ModoJuego;
import mx.uaemex.fi.model.RecordsDAO;
import mx.uaemex.fi.model.JugadoresDAO;

public class GameController {

    private ModoJuego modJuego;
    private RecordsDAO recordsDAO;
    private JugadoresDAO jugadoresDAO;

    public RecordsDAO getRecordsDAO() {
        return recordsDAO;
    }

    public void setRecordsDAO(RecordsDAO recordsDAO) {
        this.recordsDAO = recordsDAO;
    }

    public JugadoresDAO getJugadoresDAO() {
        return jugadoresDAO;
    }

    public void setJugadoresDAO(JugadoresDAO jugadoresDAO) {
        this.jugadoresDAO = jugadoresDAO;
    }
}
