package mx.uaemex.fi;

import javafx.application.Application;
import javafx.stage.Stage;
import mx.uaemex.fi.controller.LoginController;
import mx.uaemex.fi.service.JugadoresService;
import mx.uaemex.fi.service.RecordsService;
import mx.uaemex.fi.util.NavigationHelper;

public class Launcher extends Application {

    //Servicios globales que usara toda la aplicacion
    private JugadoresService serviciojugadores;
    private RecordsService servicioRecords;

    @Override
    public void start(Stage stage) {

        //Version local
       Conexion.conectarLocal();
       serviciojugadores=Conexion.getServicioJugadores();
       servicioRecords=Conexion.getServicioRecords();

        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/LoginView.fxml",
                "Login",
                controller -> {
                    LoginController lc = (LoginController) controller;
                    lc.setStage(stage);
                    lc.setServicioJugadores(serviciojugadores);
                    lc.setServiciorecords(servicioRecords);
                }
        );

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
