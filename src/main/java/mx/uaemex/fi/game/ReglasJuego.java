package mx.uaemex.fi.game;

import mx.uaemex.fi.model.data.Jugador;

public interface ReglasJuego {

    public Jugador determinarGanador(Movimiento jugador, Movimiento oponente);
}
