package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.util.NavigationHelper;

import java.util.function.UnaryOperator;

public class EditarController extends AbstractController {

    @FXML
    private Label lblLogin;

    @FXML
    private TextField fldCorreo;

    @FXML
    private PasswordField fldPasswordActual;

    @FXML
    private PasswordField fldNuevaPassword;

    @FXML
    private PasswordField fldConfirmarPassword;

    @FXML
    private Label lblError;


    @FXML
    public void initialize() {
        limitarCaracteresSinEspacios(fldCorreo, 30);           // VARCHAR(30)
        limitarCaracteresSinEspacios(fldPasswordActual, 64);  // VARCHAR(64)
        limitarCaracteresSinEspacios(fldNuevaPassword, 64);   // VARCHAR(64)
        limitarCaracteresSinEspacios(fldConfirmarPassword, 64);
    }

    private void cargarDatosJugador() {
        lblLogin.setText(jugador.getLogin());
        fldCorreo.setText(jugador.getCorreo());
    }


    @FXML
    public void onGuardarClick() {

        lblError.setText("");

        String login = lblLogin.getText();
        String correo = fldCorreo.getText().trim();
        String passwordActual = fldPasswordActual.getText();
        String nuevaPassword = fldNuevaPassword.getText();
        String confirmarPassword = fldConfirmarPassword.getText();



        if (correo.isEmpty()) {
            mostrarError("El correo es obligatorio");
            return;
        }

        if (correo.contains(" ")) {
            mostrarError("El correo no puede contener espacios");
            return;
        }

        boolean quiereCambiarPassword =
                !nuevaPassword.isBlank() || !confirmarPassword.isBlank();

        if (quiereCambiarPassword) {

            if (passwordActual.isBlank()) {
                mostrarError("Debes ingresar tu contraseña actual");
                return;
            }

            if (passwordActual.contains(" ")
                    || nuevaPassword.contains(" ")
                    || confirmarPassword.contains(" ")) {
                mostrarError("Las contraseñas no pueden contener espacios");
                return;
            }

            if (!passwordActual.equals(jugador.getPassword())) {
                mostrarError("La contraseña actual es incorrecta");
                return;
            }

            if (!nuevaPassword.equals(confirmarPassword)) {
                mostrarError("Las nuevas contraseñas no coinciden");
                return;
            }

            if (nuevaPassword.length() < 8) {
                mostrarError("La nueva contraseña debe tener al menos 8 caracteres");
                return;
            }
        }

        try {
            jugador.setLogin(login);
            jugador.setCorreo(correo);

            if (quiereCambiarPassword && !nuevaPassword.isBlank()) {
                jugador.setPassword(nuevaPassword);
            }

            servicioJugadores.actualizarJugador(jugador);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualización exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Perfil actualizado correctamente");
            alert.showAndWait();

            regresarAlMenu();

        } catch (Exception e) {
            mostrarError("Error al actualizar el perfil");
        }
    }


    @FXML
    public void onCancelarClick() {
        regresarAlMenu();
    }


    private void regresarAlMenu() {
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/MenuView.fxml",
                "Menú",
                controller -> {
                    MenuController mc = (MenuController) controller;
                    mc.setServicioJugadores(servicioJugadores);
                    mc.setServicioRecords(serviciorecords);
                    mc.setJugador(jugador);
                    mc.setStage(stage);
                }
        );
    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setStyle("-fx-text-fill: red;");
    }

    private void limitarCaracteresSinEspacios(TextField campo, int max) {
        UnaryOperator<TextFormatter.Change> filtro = change -> {

            String nuevoTexto = change.getControlNewText();

            if (nuevoTexto.contains(" ")) {
                return null;
            }

            if (nuevoTexto.length() > max) {
                return null;
            }

            return change;
        };

        campo.setTextFormatter(new TextFormatter<>(filtro));
    }

    @Override
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        if (jugador != null) {
            cargarDatosJugador();
        }
    }
}
