package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import mx.uaemex.fi.util.NavigationHelper;

public class MenuController extends AbstractController {
    @FXML
    public void onJugarClic() {
        NavigationHelper.goTo(stage,
                "/mx/uaemex/fi/PartidaView.fxml",
                "Partida",
                controller -> {
                    PartidaController pc = (PartidaController) controller;
                    pc.setServicioJugadores(servicioJugadores);
                    pc.setServicioRecords(serviciorecords);
                    pc.setStage(stage);
                    pc.setJugador(jugador);
                });
    }

    @FXML
    public void onRecordsClic() {
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
    public void onEditarClic() {
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/EditarView.fxml",
                "Editar Perfil",
                controller -> {
                    EditarController ec = (EditarController) controller;
                    ec.setServicioJugadores(servicioJugadores);
                    ec.setServicioRecords(serviciorecords);
                    ec.setStage(stage);
                    ec.setJugador(jugador);
                });
    }

}
