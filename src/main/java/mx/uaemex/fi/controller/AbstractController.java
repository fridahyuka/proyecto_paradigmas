package mx.uaemex.fi.controller;

import javafx.stage.Stage;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.service.JugadoresService;
import mx.uaemex.fi.service.RecordsService;

public abstract class AbstractController {
    protected JugadoresService servicioJugadores;
    protected RecordsService serviciorecords;
    protected Stage stage;
    protected Jugador jugador;



    public void setStage(Stage satage) {
        this.stage = satage;
    }

    public void setServicioJugadores(JugadoresService servicioJugadores) {
        this.servicioJugadores = servicioJugadores;
    }
    

    public void setServicioRecords(RecordsService serviciorecords) {
        this.serviciorecords = serviciorecords;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
