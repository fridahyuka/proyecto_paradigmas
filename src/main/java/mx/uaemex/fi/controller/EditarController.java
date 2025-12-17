package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.util.NavigationHelper;

public class EditarController extends AbstractController {

    @FXML private TextField fldLogin;
    @FXML private TextField fldCorreo;
    @FXML private PasswordField fldPasswordActual;
    @FXML private PasswordField fldNuevaPassword;
    @FXML private PasswordField fldConfirmarPassword;
    @FXML private Label lblError;

    @FXML
    public void initialize() {
        if (jugador != null) {
            cargarDatosJugador();
        }
    }

    private void cargarDatosJugador() {
        fldLogin.setText(jugador.getLogin());
        fldCorreo.setText(jugador.getCorreo());
    }

    @FXML
    public void onGuardarClic() {
        lblError.setText("");

        String login = fldLogin.getText();
        String correo = fldCorreo.getText();
        String passwordActual = fldPasswordActual.getText();
        String nuevaPassword = fldNuevaPassword.getText();
        String confirmarPassword = fldConfirmarPassword.getText();

        // Validar campos obligatorios
        if (login == null || login.isBlank() || correo == null || correo.isBlank()) {
            mostrarError("Usuario y correo son obligatorios");
            return;
        }

        // Validar longitud del login
        if (login.length() > 15) {
            mostrarError("Usuario demasiado largo, máximo 15 caracteres");
            return;
        }

        // Verificar si el usuario ya existe (excepto si es el mismo)
        if (!login.equals(jugador.getLogin())) {
            Jugador filtro = new Jugador();
            filtro.setLogin(login);
            if (!servicioJugadores.consultarUsuario(filtro).isEmpty()) {
                mostrarError("El usuario no está disponible");
                return;
            }
        }

        // Validar contraseña actual si se quiere cambiar la contraseña
        boolean quiereCambiarPassword = !nuevaPassword.isBlank() || !confirmarPassword.isBlank();

        if (quiereCambiarPassword) {
            // Verificar que se haya ingresado la contraseña actual
            if (passwordActual.isBlank()) {
                mostrarError("Debes ingresar tu contraseña actual para cambiarla");
                return;
            }

            // Verificar que la contraseña actual sea correcta
            if (!passwordActual.equals(jugador.getPassword())) {
                mostrarError("La contraseña actual es incorrecta");
                return;
            }

            // Validar nueva contraseña
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
            // Actualizar datos del jugador
            jugador.setLogin(login);
            jugador.setCorreo(correo);

            // Actualizar contraseña si se proporcionó una nueva
            if (quiereCambiarPassword && !nuevaPassword.isBlank()) {
                jugador.setPassword(nuevaPassword);
            }

            // Guardar cambios
            servicioJugadores.actualizarJugador(jugador);

            // Mostrar mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualización exitosa");
            alert.setHeaderText(null);
            alert.setContentText("Perfil actualizado correctamente");
            alert.showAndWait();

            // Regresar al menú
            regresarAlMenu();

        } catch (Exception e) {
            mostrarError("Error al actualizar el perfil: " + e.getMessage());
        }
    }

    @FXML
    public void onCancelarClic() {
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
                }
        );
    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setStyle("-fx-text-fill: red;");
    }

    @Override
    public void setJugador(Jugador jugador) {
        super.setJugador(jugador);
        cargarDatosJugador();
    }
}