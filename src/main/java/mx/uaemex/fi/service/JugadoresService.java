package mx.uaemex.fi.service;

import mx.uaemex.fi.model.data.Jugador;

import java.util.ArrayList;

public interface JugadoresService extends Service{
    public ArrayList<Jugador> consultarUsuario(Jugador j);
    public ArrayList<Jugador> consultar();
    public Jugador registrarJugador(Jugador j);
    public boolean eliminarJugador(Jugador j);
    public Jugador actualizarJugador(Jugador j);
}
