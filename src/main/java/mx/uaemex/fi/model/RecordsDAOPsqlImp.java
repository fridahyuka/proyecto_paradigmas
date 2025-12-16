package mx.uaemex.fi.model;

import mx.uaemex.fi.model.data.Juego;
import mx.uaemex.fi.model.data.Record;
import mx.uaemex.fi.model.data.Jugador;

import java.sql.*;
import java.util.ArrayList;

public class RecordsDAOPsqlImp extends AbstractSqlDAO implements RecordsDAO {

    @Override
    public void insertar(Record record) {
        String sql = "INSERT INTO records(jugador_id, juego_id, record) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, record.getJugador().getId());
            ps.setInt(2, record.getJuego().getId());
            ps.setInt(3, record.getRecord());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Record> consultar() {
        ArrayList<Record> lista = new ArrayList<>();
        String sql = "SELECT * FROM records";

        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Jugador jugador = new Jugador();
                jugador.setId(rs.getInt("jugador_id"));

                Juego juego = new Juego();
                juego.setId(rs.getInt("juego_id"));

                Record r = new Record();
                r.setId(rs.getInt("id"));
                r.setJugador(jugador);
                r.setJuego(juego);
                r.setRecord(rs.getInt("record"));

                lista.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void actualizar(Record record) {
        String sql = "UPDATE records SET jugador_id=?, juego_id=?, record=? WHERE id=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, record.getJugador().getId());
            ps.setInt(2, record.getJuego().getId());
            ps.setInt(3, record.getRecord());
            ps.setInt(4, record.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void borrar(Record record) {
        String sql = "DELETE FROM records WHERE id=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, record.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
