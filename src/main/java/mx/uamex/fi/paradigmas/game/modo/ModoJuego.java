package mx.uamex.fi.paradigmas.game.modo;

import mx.uamex.fi.paradigmas.model.data.Jugador;
import mx.uamex.fi.paradigmas.game.Movimiento;

public interface ModoJuego {

    public Movimiento obtenerMovimientoOponente(Jugador j);

    public Movimiento procesarPartida(Jugador j, Movimiento mJugador);

}