package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.service.local.JugadoresServicesLocal;
import mx.uaemex.fi.util.NavigationHelper;

import java.util.List;
import java.util.Optional;

public class LoginController extends AbstractController {

    @FXML
    private TextField fldUser;

    @FXML
    private PasswordField fldPassword;

    @FXML
    private Label lblMessage;

    @FXML
    public void onEntrarClick() {

        lblMessage.setText("");

        String usuario = fldUser.getText();
        String contrasena = fldPassword.getText();

        // Validaciones básicas
        if (usuario == null || usuario.isBlank()) {
            lblMessage.setText("Ingresa un usuario");
            return;
        }

        if (contrasena == null || contrasena.isBlank()) {
            lblMessage.setText("Ingresa una contraseña");
            return;
        }

        // Buscar usuario
        Jugador filtro = new Jugador();
        filtro.setLogin(usuario);

        List<Jugador> encontrados = servicioJugadores.consultarUsuario(filtro);

        if (encontrados.isEmpty()) {
            lblMessage.setText("Usuario no encontrado");
            return;
        }

        Jugador j = encontrados.get(0);

        System.out.println("LA ID POR DEFECTO ES " + j.getId());

        if (!servicioJugadores.login(j, contrasena)) {
            lblMessage.setText("Contraseña incorrecta");
            return;
        }

        if (servicioJugadores instanceof JugadoresServicesLocal) {

            // Si la cuenta está inactiva → preguntar reactivación
            if (!j.isActivo()) {

                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Cuenta eliminada");
                confirmacion.setHeaderText("Tu cuenta está desactivada");
                confirmacion.setContentText("¿Deseas recuperar esta cuenta?");

                Optional<ButtonType> resultado = confirmacion.showAndWait();

                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    j.setActivo(true);
                    servicioJugadores.actualizarJugador(j);
                } else {
                    lblMessage.setText("usuario incorrecto");
                    return;
                }
            }

        }

        j.setPassword(contrasena);

        // Login exitoso
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/PartidaView.fxml",
                "Partida",
                controller -> {
                    PartidaController pc = (PartidaController) controller;
                    pc.setServicioJugadores(servicioJugadores);
                    pc.setServicioRecords(serviciorecords);
                    pc.setJugador(j);
                    pc.setStage(stage);
                });
    }

    @FXML
    public void onRegistrarClick() {
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/RegistroView.fxml",
                "Registro",
                controller -> {
                    RegistroController rc = (RegistroController) controller;
                    rc.setServicioJugadores(servicioJugadores);
                    rc.setServicioRecords(serviciorecords);
                    rc.setStage(stage);
                });
    }
}
