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
    @FXML private PasswordField fldConfirmarP;
    @FXML private Label lblError;


    @FXML
    public void onRegistrarClic() {

        lblError.setText("");

        String login = fldLogin.getText();
        String password = fldPassword.getText();
        String confirmacion= fldConfirmarP.getText();
        String correo = fldCorreo.getText();

        //Validar entradas
        if (login == null || login.isBlank()
                || password == null || password.isBlank()
                || correo == null || correo.isBlank()
                || confirmacion==null || confirmacion.isBlank() ) {

            mostrarError("Llena todos los campos");
            return;
        }
        if(login.length()>15){
            mostrarError("Usuario demasiado largo, maximo 15 caracteres");
        }
        if(!password.equals(confirmacion)){
            mostrarError("Las contraseñas no coinciden");
            return;
        }

        if (password.length() < 8) {
            mostrarError("La contraseña debe tener almenos 8 caracteres");
            return;
        }

        Jugador filtro = new Jugador();
        filtro.setLogin(login);

        if (!servicioJugadores.consultarUsuario(filtro).isEmpty()) {
            mostrarError("El usuario no está disponible");
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


        // Abrir ventana del juego

        NavigationHelper.goTo(stage,
                "/mx/uaemex/fi/PartidaView.fxml",
                "Partida",
                controller->{
                    PartidaController pc= (PartidaController) controller;
                    pc.setServicioJugadores(servicioJugadores);
                    pc.setServicioRecords(serviciorecords);
                    pc.setStage(stage);
                    pc.setJugador(nuevo);
                }
        );
    }

    @FXML
    public void onIniciarSesionClic() {
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

}
