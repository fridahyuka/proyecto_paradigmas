package mx.uamex.fi.paradigmas.model;

import mx.uamex.fi.paradigmas.data.Jugador;
import java.util.ArrayList;

public interface JugadoresDAO {
    void insertar(Jugador jugador);
    ArrayList<Jugador> consultar();
    void actualizar(Jugador jugador);
    void borrar(Jugador jugador);
}
