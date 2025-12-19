package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.util.NavigationHelper;

import java.util.Optional;

public class MenuController extends AbstractController {

    @FXML private Label lblUsuario;
    @FXML private Label lblCorreo;


    @FXML
    public void onJugarClick() {
        NavigationHelper.goTo(stage,
                "/mx/uaemex/fi/PartidaView.fxml",
                "Partida",
                controller -> {
                    PartidaController pc = (PartidaController) controller;
                    pc.setServicioJugadores(servicioJugadores);
                    pc.setServicioRecords(serviciorecords);
                    pc.setStage(stage);
                    pc.setJugador(this.jugador);
                });
    }

    @FXML
    public void onRecordsClick() {
        NavigationHelper.goTo(stage,
                "/mx/uaemex/fi/RecordsView.fxml",
                "Records",
                controller -> {
                    RecordsController rc = (RecordsController) controller;
                    rc.setServicioJugadores(servicioJugadores);
                    rc.setServicioRecords(serviciorecords);
                    rc.setJugador(this.jugador);
                    rc.setStage(stage);
                });
    }

    @FXML
    public void onEditarClick() {
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/EditarView.fxml",
                "Editar Perfil",
                controller -> {

                    EditarController ec = (EditarController) controller;
                    ec.setJugador(this.jugador);
                    ec.setServicioJugadores(servicioJugadores);
                    ec.setServicioRecords(serviciorecords);
                    ec.setStage(stage);
                });
    }

    @FXML
    public void onCerrarSesionClick(){
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/LoginView.fxml",
                "Login",
                controller -> {
                    LoginController lc = (LoginController) controller;
                    lc.setStage(stage);
                    lc.setServicioJugadores(servicioJugadores);
                    lc.setServicioRecords(this.serviciorecords);
                    lc.setJugador(null);
                });
    }

    @FXML
    public void onEliminarClick() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText("¿Eliminar cuenta?");
        alert.setContentText(
                "Esta acción desactivará tu cuenta.\n" +
                        "No podrás iniciar sesión nuevamente.\n\n" +
                        "¿Deseas continuar?"
        );

        // Botones personalizados
        ButtonType btnSi = new ButtonType("Sí, eliminar");
        ButtonType btnNo = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnSi, btnNo);

        // Mostrar alerta y esperar respuesta
        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent() && resultado.get() == btnSi) {
            try {
                jugador.setActivo(false);
                servicioJugadores.actualizarJugador(jugador);

                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Cuenta eliminada");
                info.setHeaderText(null);
                info.setContentText("Tu cuenta ha sido desactivada correctamente.");
                info.showAndWait();

                // Regresar a Login
                NavigationHelper.goTo(
                        stage,
                        "/mx/uaemex/fi/LoginView.fxml",
                        "Inicio de sesión",
                        controller -> {
                            LoginController lc = (LoginController) controller;
                            lc.setServicioJugadores(servicioJugadores);
                            lc.setServicioRecords(serviciorecords);
                            lc.setStage(stage);
                        });

            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("No se pudo eliminar la cuenta");
                error.setContentText(e.getMessage());
                error.showAndWait();
            }
        }
    }


    @Override
    public void setJugador(Jugador jugador){
        this.jugador=jugador;
        carcarDatos();
    }
    private void carcarDatos(){
        lblUsuario.setText(this.jugador.getLogin());
        lblCorreo.setText(this.jugador.getCorreo());
    }

}
