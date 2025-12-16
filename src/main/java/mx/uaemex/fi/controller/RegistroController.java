package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.util.NavigationHelper;

public class RegistroController extends AbstractController{

    @FXML private TextField fldLogin;
    @FXML private PasswordField fldPassword;
    @FXML private TextField fldCorreo;
    @FXML private PasswordField fldComprobar;
    @FXML private Label error;


    @FXML
    public void onRegistrarClic() {

        error.setText(""); // limpiar errores previos

        String login = fldLogin.getText();
        String password = fldPassword.getText();
        String correo = fldCorreo.getText();
        String comprobar= fldComprobar.getText();

        // 🔐 Validaciones
        if (login == null || login.isBlank()
                || password == null || password.isBlank()
                || correo == null || correo.isBlank()
                || comprobar==null || comprobar.isBlank() ) {

            mostrarError("Llena todos los campos");
            return;
        }
        if(!password.equals(comprobar)){
            mostrarError("Las contraseñas no coinciden");
            return;
        }

        if (password.length() < 8) {
            mostrarError("La contraseña debe tener almenos 8 caracteres");
            return;
        }

        Jugador filtro = new Jugador();
        filtro.setLogin(login);

        if (!servicio.consultarUsuario(filtro).isEmpty()) {
            mostrarError("El usuario no está disponible");
            return;
        }


        // 🧾 Registro
        Jugador nuevo = new Jugador();
        nuevo.setLogin(login);
        nuevo.setPassword(password);
        nuevo.setCorreo(correo);
        nuevo.setActivo(true);

        servicio.registrarJugador(nuevo);

        // ✅ ÉXITO → Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Jugador guardado correctamente");
        alert.showAndWait();


        //abre la ventana del jueg
    }

    @FXML
    public void onIniciarSesionClic() {
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/LoginView.fxml",
                "Login",
                controller -> {
                    LoginController lc = (LoginController) controller;
                    lc.setServicio(servicio);
                    lc.setStage(stage);
                }
        );
    }


    private void mostrarError(String mensaje) {
        error.setText(mensaje);
        error.setStyle("-fx-text-fill: red;");
    }

}
