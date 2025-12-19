package mx.uaemex.fi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.model.data.Record;
import mx.uaemex.fi.service.RecordsService;
import mx.uaemex.fi.util.NavigationHelper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class RecordsController extends AbstractController {

    @FXML
    private TableView<Record> tblRecords;

    @FXML
    private TableColumn<Record, Integer> colRecord;

    @FXML
    private TableColumn<Record, Date> colFecha;

    @FXML
    private Label lblMejorRecord;

    private ObservableList<Record> listaRecords;

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @FXML
    public void initialize() {

        listaRecords = FXCollections.observableArrayList();
        tblRecords.setItems(listaRecords);


        colRecord.setCellValueFactory(new PropertyValueFactory<>("record"));
        colRecord.setCellFactory(col -> new TableCell<Record, Integer>() {
            @Override
            protected void updateItem(Integer valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                } else {
                    setText(valor.toString());
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });


        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setCellFactory(col -> new TableCell<Record, Date>() {
            @Override
            protected void updateItem(Date fecha, boolean empty) {
                super.updateItem(fecha, empty);

                if (empty || fecha == null) {
                    setText(null);
                } else {
                    LocalDate localDate = fecha.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

                    setText(localDate.format(FORMATO_FECHA));
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });
    }

    private void cargarRecords() {

        if (jugador == null || serviciorecords == null) {
            return;
        }

        try {
            List<Record> records = serviciorecords.consultar(jugador);
            listaRecords.setAll(records);

            if (records.isEmpty()) {
                lblMejorRecord.setText("Juega primero para crear tu primer récord 🎮");
                return;
            }

            Record max = serviciorecords.consultarMax(jugador);

            if (max != null && max.getFecha() != null) {

                LocalDate fecha =
                        ((java.sql.Date) max.getFecha()).toLocalDate();

                lblMejorRecord.setText(
                        "Tu mejor marca: " +
                                max.getRecord() +
                                " victorias, fecha " +
                                fecha.format(FORMATO_FECHA)
                );

            } else {
                lblMejorRecord.setText("Juega primero para crear tu primer récord 🎮");
            }

        } catch (Exception e) {
            e.printStackTrace();
            lblMejorRecord.setText("Error al cargar récords");
        }
    }


    @Override
    public void setJugador(Jugador jugador) {
        super.setJugador(jugador);
        cargarRecordsSiListo();
    }

    @Override
    public void setServicioRecords(RecordsService serviciorecords) {
        super.setServicioRecords(serviciorecords);
        cargarRecordsSiListo();
    }

    private void cargarRecordsSiListo() {
        if (jugador != null && serviciorecords != null) {
            cargarRecords();
        }
    }

    @FXML
    public void onMenuClic() {
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
                });
    }

    @FXML
    public void onJugarClic() {
        NavigationHelper.goTo(
                stage,
                "/mx/uaemex/fi/PartidaView.fxml",
                "Partida",
                controller -> {
                    PartidaController pc = (PartidaController) controller;
                    pc.setServicioJugadores(servicioJugadores);
                    pc.setServicioRecords(serviciorecords);
                    pc.setJugador(jugador);
                    pc.setStage(stage);
                });
    }

    @FXML
    public void onEditarClic() {
        // pendiente
    }
}
