package mx.uaemex.fi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.model.data.Record;
import mx.uaemex.fi.service.RecordsService;
import mx.uaemex.fi.util.NavigationHelper;

import java.util.List;

public class RecordsController extends AbstractController {

    @FXML
    private TableView<Record> tblRecords;

    @FXML
    private TableColumn<Record, Integer> colRecord;

    @FXML
    private TableColumn<Record, Object> colFecha; // Object para Date (así evitas problemas por ahora)

    private ObservableList<Record> listaRecords;

    @FXML
    public void initialize() {
        // Inicializar la lista observable
        listaRecords = FXCollections.observableArrayList();
        tblRecords.setItems(listaRecords);

        // Enlazar columnas con atributos del modelo
        colRecord.setCellValueFactory(new PropertyValueFactory<>("record"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    /**
     * Se llama después de setJugador y setServicioRecords
     */
    private void cargarRecords() {
        // Verificar que ambos componentes necesarios estén inicializados
        if (this.jugador == null || this.serviciorecords == null) {
            System.out.println("Falta jugador o servicio para cargar records");
            return;
        }

        try {
            List<Record> records = serviciorecords.consultar(jugador);
            listaRecords.setAll(records);
            System.out.println("Records cargados: " + records.size());
        } catch (Exception e) {
            System.err.println("Error al cargar records: " + e.getMessage());
            e.printStackTrace();
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

    /**
     * Método auxiliar para cargar records solo cuando ambos componentes estén listos
     */
    private void cargarRecordsSiListo() {
        // Solo cargar si ambos están inicializados
        if (this.jugador != null && this.serviciorecords != null) {
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
                }
        );
    }

    @FXML public void onJugarClic(){
        NavigationHelper.goTo(stage,
                "/mx/uaemex/fi/PartidaView.fxml",
                "Partida",
                controller->{
                    PartidaController pc= (PartidaController) controller;
                    pc.setServicioJugadores(servicioJugadores);
                    pc.setServicioRecords(serviciorecords);
                    pc.setStage(stage);
                    pc.setJugador(this.jugador);
                }
        );
    }
    @FXML public void onEditarClic(){

    }

}