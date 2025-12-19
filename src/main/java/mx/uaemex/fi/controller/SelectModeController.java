package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import mx.uaemex.fi.service.JugadoresService;
import mx.uaemex.fi.service.RecordsService;
import mx.uaemex.fi.service.local.ConexionPostgres;
import mx.uaemex.fi.service.online.JugadoresServicesOnline;
import mx.uaemex.fi.service.online.RecordsServiceOnline;
import mx.uaemex.fi.util.NavigationHelper;

public class SelectModeController extends AbstractController {
    private JugadoresService servicioJugadores;
    private RecordsService servicioRecords;

    @FXML
    public void selectLocal() {

        ConexionPostgres.conectar();
        servicioJugadores = ConexionPostgres.getServicioJugadores();
        servicioRecords = ConexionPostgres.getServicioRecords();

        this.setServicioJugadores(servicioJugadores);
        this.setServicioRecords(serviciorecords);

        this.goToLogin();

    }

    @FXML
    public void selectOnline() {
        this.setServicioJugadores(new JugadoresServicesOnline());
        this.setServicioRecords(new RecordsServiceOnline());

        this.goToLogin();
    }

    public JugadoresService getServicioJugadores() {
        return servicioJugadores;
    }

    public void setServicioJugadores(JugadoresService servicioJugadores) {
        this.servicioJugadores = servicioJugadores;
    }

    public RecordsService getServicioRecords() {
        return servicioRecords;
    }

    public void setServicioRecords(RecordsService servicioRecords) {
        this.servicioRecords = servicioRecords;
    }

    public void goToLogin() {

        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/LoginView.fxml",
                "Login",
                controller -> {
                    LoginController lc = (LoginController) controller;
                    lc.setStage(stage);
                    lc.setServicioJugadores(servicioJugadores);
                    lc.setServicioRecords(servicioRecords);
                    lc.setStage(stage);
                });

        stage.show();
    }

}
