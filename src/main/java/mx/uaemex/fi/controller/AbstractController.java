package mx.uaemex.fi.controller;

import javafx.stage.Stage;
import mx.uaemex.fi.service.JugadoresService;

public abstract class AbstractController {
    protected JugadoresService servicio;
    protected Stage stage;

    public JugadoresService getServicio() {
        return servicio;
    }

    public void setServicio(JugadoresService servicio) {
        this.servicio = servicio;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage satage) {
        this.stage = satage;
    }
}
