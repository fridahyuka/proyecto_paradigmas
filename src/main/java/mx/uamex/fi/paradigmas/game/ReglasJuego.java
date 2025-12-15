package mx.uamex.fi.paradigmas.game;

import mx.uamex.fi.paradigmas.model.data.Jugador;

public interface ReglasJuego {

    public Jugador determinarGanador(Movimiento jugador, Movimiento oponente);
}
