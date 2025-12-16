package mx.uaemex.fi.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class NavigationHelper {

    public static void goTo(Stage stage, String fxmlPath, String windowTitle,
                            Consumer<Object> controllerInit) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationHelper.class.getResource(fxmlPath));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controllerInit != null) {
                controllerInit.accept(controller);
            }

            Scene scene = new Scene(root);

            stage.setTitle(windowTitle);
            stage.setScene(scene);
            stage.setResizable(false); // opcional

            // 🚫 NO modificar tamaño → usa el del FXML

        } catch (IOException e) {
            System.err.println("Error cargando escena: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static void goTo(Stage stage, String fxmlPath, String windowTitle) {
        goTo(stage, fxmlPath, windowTitle, null);
    }
}
