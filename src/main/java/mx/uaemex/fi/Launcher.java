package mx.uaemex.fi;

import javafx.application.Application;
import javafx.stage.Stage;
import mx.uaemex.fi.controller.SelectModeController;
import mx.uaemex.fi.util.NavigationHelper;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) {

        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/SelectModeView.fxml",
                "Login",
                controller -> {
                    SelectModeController lc = (SelectModeController) controller;
                    lc.setStage(stage);
                });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
