package mx.uamex.fi.paradigmas.session;

import mx.uamex.fi.paradigmas.model.data.Jugador;

public class Partida {

    private Jugador jugador;
    private Movimiento movJugador;
    private Movimiento movOponente;
    private Resultado resultado;

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Movimiento getMovJugador() {
        return movJugador;
    }

    public void setMovJugador(Movimiento movJugador) {
        this.movJugador = movJugador;
    }

    public Movimiento getMovOponente() {
        return movOponente;
    }

    public void setMovOponente(Movimiento movOponente) {
        this.movOponente = movOponente;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
}
