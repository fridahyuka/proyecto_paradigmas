package mx.uaemex.fi.controller;

import javafx.stage.Stage;
import mx.uaemex.fi.service.JugadoresService;
import mx.uaemex.fi.service.RecordsService;

public abstract class AbstractController {
    protected JugadoresService servicioJugadores;
    protected RecordsService serviciorecords;
    protected Stage stage;


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage satage) {
        this.stage = satage;
    }

    public JugadoresService getServicioJugadores() {
        return servicioJugadores;
    }

    public void setServicioJugadores(JugadoresService servicioJugadores) {
        this.servicioJugadores = servicioJugadores;
    }

    public RecordsService getServicioRecords() {
        return serviciorecords;
    }

    public void setServicioRecords(RecordsService serviciorecords) {
        this.serviciorecords = serviciorecords;
    }
}
