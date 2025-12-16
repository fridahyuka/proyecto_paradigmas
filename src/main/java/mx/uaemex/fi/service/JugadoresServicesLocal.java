package mx.uaemex.fi.service;

import mx.uaemex.fi.model.JugadoresDAO;
import mx.uaemex.fi.model.data.Jugador;

import java.util.ArrayList;

public class JugadoresServicesLocal implements JugadoresService{
    private JugadoresDAO dao;

    public void setDao(JugadoresDAO dao) {
        this.dao = dao;
    }

    @Override
    public ArrayList<Jugador> consultar() {
        return this.dao.consultar();
    }

    @Override
    public ArrayList<Jugador> consultarUsuario(Jugador j) {
        return this.dao.consultar(j);
    }

    @Override
    public Jugador registrarJugador(Jugador j) {
        return this.dao.insertar(j);
    }

    @Override
    public boolean eliminarJugador(Jugador j) {
        this.dao.eliminar(j);
        return true;
    }

    @Override
    public Jugador actualizarJugador(Jugador j) {
        this.dao.actualizar(j);
        return j;
    }
}
