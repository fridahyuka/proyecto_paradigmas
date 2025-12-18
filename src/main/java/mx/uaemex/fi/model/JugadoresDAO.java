package mx.uaemex.fi.model;

import mx.uaemex.fi.model.data.Jugador;

import java.util.ArrayList;

public interface JugadoresDAO {
    public Jugador insertar(Jugador jugador);

    public ArrayList<Jugador> consultar();

    public ArrayList<Jugador> consultar(Jugador jugador);

    public void actualizar(Jugador jugador);

    public void eliminar(Jugador jugador);
}
