package mx.uaemex.fi.controller;

import javafx.fxml.FXML;
import mx.uaemex.fi.util.NavigationHelper;

public class MenuController extends AbstractController {

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

}
