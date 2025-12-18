package mx.uaemex.fi.model.data;

import java.util.Date;

public class Record extends ElementoConID implements Data {
    private Jugador jugador;
    private int record;
    private Date fecha;

    public Record() {
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Record [jugador: " + jugador + ", record: " + record + ", fecha: " + fecha + "]";
    }

}
