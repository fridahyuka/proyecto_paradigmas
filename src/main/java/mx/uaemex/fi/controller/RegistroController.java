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

public class RegistroController extends AbstractController {

    @FXML
    private TextField fldLogin;

    @FXML
    private PasswordField fldPassword;

    @FXML
    private PasswordField fldConfirmarP;

    @FXML
    private TextField fldCorreo;

    @FXML
    private Label lblError;

    @FXML
    public void initialize() {
        // Coinciden con la BD
        limitarCaracteresSinEspacios(fldLogin, 15);      // VARCHAR(15)
        limitarCaracteresSinEspacios(fldPassword, 64);   // VARCHAR(64)
        limitarCaracteresSinEspacios(fldConfirmarP, 64); // VARCHAR(64)
        limitarCaracteresSinEspacios(fldCorreo, 30);     // VARCHAR(30)
    }

    @FXML
    public void onRegistrarClick() {

        lblError.setText("");

        String login = fldLogin.getText().trim();
        String password = fldPassword.getText();
        String confirmacion = fldConfirmarP.getText();
        String correo = fldCorreo.getText().trim();


        if (login.isEmpty() || password.isEmpty()
                || confirmacion.isEmpty() || correo.isEmpty()) {
            mostrarError("Llena todos los campos");
            return;
        }

        // Refuerzo: nunca permitir espacios
        if (login.contains(" ") || password.contains(" ")
                || confirmacion.contains(" ") || correo.contains(" ")) {
            mostrarError("No se permiten espacios en los campos");
            return;
        }

        if (password.length() < 8) {
            mostrarError("La contraseña debe tener al menos 8 caracteres");
            return;
        }

        if (!password.equals(confirmacion)) {
            mostrarError("Las contraseñas no coinciden");
            return;
        }

        Jugador filtroLogin = new Jugador();
        filtroLogin.setLogin(login);

        if (!servicioJugadores.consultarUsuario(filtroLogin).isEmpty()) {
            mostrarError("El usuario no está disponible");
            return;
        }

        Jugador filtroCorreo = new Jugador();
        filtroCorreo.setCorreo(correo);

        if (!servicioJugadores.consultarUsuario(filtroCorreo).isEmpty()) {
            mostrarError("El correo ya está registrado");
            return;
        }


        Jugador nuevo = new Jugador();
        nuevo.setLogin(login);
        nuevo.setPassword(password);
        nuevo.setCorreo(correo);
        nuevo.setActivo(true);

        servicioJugadores.registrarJugador(nuevo);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Jugador guardado correctamente");
        alert.showAndWait();


        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/PartidaView.fxml",
                "Partida",
                controller -> {
                    PartidaController pc = (PartidaController) controller;
                    pc.setServicioJugadores(servicioJugadores);
                    pc.setServicioRecords(serviciorecords);
                    pc.setStage(stage);
                    pc.setJugador(nuevo);
                }
        );
    }


    @FXML
    public void onIniciarSesionClick() {
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/LoginView.fxml",
                "Login",
                controller -> {
                    LoginController lc = (LoginController) controller;
                    lc.setServicioJugadores(servicioJugadores);
                    lc.setServicioRecords(serviciorecords);
                    lc.setStage(stage);
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
}

