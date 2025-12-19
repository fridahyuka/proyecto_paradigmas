package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mx.uaemex.fi.game.Movimiento;
import mx.uaemex.fi.game.ReglasPPT;
import mx.uaemex.fi.game.Resultado;
import mx.uaemex.fi.model.data.Record;
import mx.uaemex.fi.util.NavigationHelper;

import java.util.Date;
import java.util.Objects;

import java.util.Random;

public class PartidaController extends AbstractController {

    @FXML
    private Label lblResultado;
    @FXML
    private Label lblMensaje;
    @FXML
    private Label lblOponente;
    @FXML
    private Label lblJugador;
    @FXML
    private Button btnIniciar;
    @FXML
    private Label lblConteo;

    @FXML
    private ImageView imgOponente;
    @FXML
    private ImageView imgJugador;

    private final ReglasPPT reglas = new ReglasPPT();
    private final Random random = new Random();
    private boolean partidaIniciada = false;
    private int victorias;

    @FXML
    public void onIniciarClic() {
        partidaIniciada = true;
        lblMensaje.setVisible(true);
        lblJugador.setVisible(false);
        lblOponente.setVisible(false);
        lblResultado.setVisible(false);
        btnIniciar.setVisible(false);
        imgJugador.setImage(null);
        imgOponente.setImage(null);


    }

    @FXML
    public void onPiedraClick(MouseEvent e) {
        jugar(Movimiento.PIEDRA);
    }

    @FXML
    public void onPapelClick(MouseEvent e) {
        jugar(Movimiento.PAPEL);
    }

    @FXML
    public void onTijeraClick(MouseEvent e) {
        jugar(Movimiento.TIJERA);
    }

    @FXML
    public void onLagartoClick(MouseEvent e) {
        jugar(Movimiento.LAGARTO);
    }

    @FXML
    public void onSpockClick(MouseEvent e) {
        jugar(Movimiento.SPOCK);
    }

    @FXML
    public void onMenuClick() {
        if(this.jugador==null){
            System.out.println("jugador nulo");
            return;
        }
        NavigationHelper.goTo(stage,
                "/mx/uaemex/fi/MenuView.fxml",
                "Menú",
                controller -> {
                    MenuController mc = (MenuController) controller;
                    mc.setServicioJugadores(servicioJugadores);
                    mc.setServicioRecords(serviciorecords);
                    mc.setJugador(this.jugador);
                    mc.setStage(stage);
                });
    }

    private void jugar(Movimiento jugador) {

        if (this.jugador == null) {
            lblMensaje.setText("Jugador no encontrado...");
            return;
        }

        if (!partidaIniciada) {
            return;
        }

        Movimiento oponente = Movimiento.values()[random.nextInt(Movimiento.values().length)];
        Resultado resultado = reglas.determinarGanador(jugador, oponente);

        if (resultado == Resultado.GANASTE) {
            victorias++;
        } else if (resultado == Resultado.PERDISTE) {
            // si pierde crea un record, lo guarda, y reinicia el conteo. Si pierde en el
            // primer jugada no crea el record

            if (victorias == 0) {
                return;
            }

            Record record = new Record();
            record.setJugador(this.jugador);
            record.setRecord(victorias);
            record.setFecha(new Date());

            serviciorecords.insertar(record);

            victorias = 0;
        }

        lblConteo.setText("" + victorias);
        mostrarMovimiento(imgJugador, jugador);
        mostrarMovimiento(imgOponente, oponente);

        lblJugador.setText("Tú:");
        lblOponente.setText("Computadora:");

        lblJugador.setVisible(true);
        lblOponente.setVisible(true);

        lblResultado.setText(resultado.toString());
        lblResultado.setVisible(true);

        btnIniciar.setText("Volver a jugar");
        btnIniciar.setVisible(true);
        partidaIniciada = false;
    }

    private void mostrarMovimiento(ImageView img, Movimiento m) {
        img.setImage(new Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream(
                                "/mx/uaemex/fi/images/" + m.name().toLowerCase() + ".png"),
                        "No se encontró la imagen de " + m)));
    }

}
