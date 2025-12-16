package mx.uaemex.fi.game.modo;

import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.game.Movimiento;

public interface ModoJuego {

    public Movimiento obtenerMovimientoOponente(Jugador j);

    public Movimiento procesarPartida(Jugador j, Movimiento mJugador);

}