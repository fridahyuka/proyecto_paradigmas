package mx.uaemex.fi;

import javafx.application.Application;
import javafx.stage.Stage;
import mx.uaemex.fi.controller.LoginController;
import mx.uaemex.fi.service.JugadoresService;
import mx.uaemex.fi.util.NavigationHelper;

public class Launcher extends Application {

    private JugadoresService servicio;//el servicio global que usara toda la aplicacion

    @Override
    public void start(Stage stage) {

        // 🔌 Inicializar conexión y service
        servicio = Conexion.conectarLocal();
        // ⬆ este método DEBE devolver JugadoresService

        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/LoginView.fxml",
                "Login",
                controller -> {
                    LoginController lc = (LoginController) controller;
                    lc.setStage(stage);
                    lc.setServicio(servicio);
                }
        );

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
