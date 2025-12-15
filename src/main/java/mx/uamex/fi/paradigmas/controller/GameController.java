package mx.uamex.fi.paradigmas.controller;

import mx.uamex.fi.paradigmas.game.modo.ModoJuego;
import mx.uamex.fi.paradigmas.model.RecordsDAO;
import mx.uamex.fi.paradigmas.model.JugadoresDAO;

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
