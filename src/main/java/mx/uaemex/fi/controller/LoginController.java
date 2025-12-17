package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.util.NavigationHelper;

import java.util.ArrayList;

public class LoginController extends AbstractController {

    @FXML private TextField fldUser;
    @FXML private PasswordField fldPassword;
    @FXML private Label lblMessage;


    @FXML
    public void onEntrarClick() {

        if (servicioJugadores == null) {
            lblMessage.setText("Servicio no inicializado");
            return;
        }

        String usuario = fldUser.getText();
        String contrasena = fldPassword.getText();

        if (usuario == null || usuario.isBlank()) {
            lblMessage.setText("Ingresa un usuario");
            return;
        }

        if (contrasena == null || contrasena.isBlank()) {
            lblMessage.setText("Ingresa una contraseña");
            return;
        }

        Jugador filtro = new Jugador();
        filtro.setLogin(usuario);

        ArrayList<Jugador> encontrados = servicioJugadores.consultarUsuario(filtro);

        if (encontrados.isEmpty()) {
            lblMessage.setText("Usuario no encontrado");
            return;
        }

        Jugador j = encontrados.get(0);

        if (!j.isActivo()) {
            lblMessage.setText("Jugador no activo 😔");
            return;
        }

        if (!j.getPassword().equals(contrasena)) {
            lblMessage.setText("Contraseña incorrecta");
            return;
        }

        lblMessage.setText("✔ Bienvenido " + usuario);
        System.out.println("Sesión iniciada con éxito");

        // Abrir ventana del juego



        NavigationHelper.goTo(stage,
                "/mx/uaemex/fi/PartidaView.fxml",
                "Partida",
                controller->{
                    PartidaController pc= (PartidaController) controller;
                    pc.setServicioJugadores(servicioJugadores);
                    pc.setServicioRecords(serviciorecords);
                    pc.setStage(stage);
                    pc.setJugador(j);
                }
        );


    }

    @FXML
    public void onRegistrarClic() {
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/RegistroView.fxml",
                "Registrarse",
                controller -> {
                    RegistroController rc = (RegistroController) controller;
                    rc.setServicioJugadores(servicioJugadores);
                    rc.setServicioRecords(serviciorecords);
                    rc.setStage(stage);
                }
        );
    }

}
