package mx.uamex.fi.paradigmas.model.data;

import java.util.Date;

public class Record implements Data {
    private Jugador jugador;
    private String juego;
    private Date fecha;

    public Record() {
    }

    public Record(Jugador jugador, String juego, Date fecha) {
        this.jugador = jugador;
        this.juego = juego;
        this.fecha = fecha;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
