package mx.uamex.fi.paradigmas.model;

import mx.uamex.fi.paradigmas.data.Record;
import mx.uamex.fi.paradigmas.data.Jugador;

import java.sql.*;
import java.util.ArrayList;

public class RecordsDAOPsqlImp extends AbstractSqlDAO implements RecordsDAO {

    @Override
    public void insertar(Record record) {
        String sql = "INSERT INTO records(jugador, juego, fecha) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, record.getJugador().getLogin());
            ps.setString(2, record.getJuego());
            ps.setDate(3, new java.sql.Date(record.getFecha().getTime()));
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
                Jugador j = new Jugador();
                j.setLogin(rs.getString("jugador"));

                Record r = new Record(
                        j,
                        rs.getString("juego"),
                        rs.getDate("fecha")
                );

                lista.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void actualizar(Record record) {
        String sql = "UPDATE records SET juego=?, fecha=? WHERE jugador=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, record.getJuego());
            ps.setDate(2, new Date(record.getFecha().getTime()));
            ps.setString(3, record.getJugador().getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrar(Record record) {
        String sql = "DELETE FROM records WHERE jugador=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, record.getJugador().getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
