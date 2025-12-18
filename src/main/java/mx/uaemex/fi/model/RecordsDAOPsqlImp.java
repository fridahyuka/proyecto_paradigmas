package mx.uaemex.fi.model;

import mx.uaemex.fi.model.data.Record;
import mx.uaemex.fi.model.data.Jugador;

import java.sql.*;
import java.util.ArrayList;

public class RecordsDAOPsqlImp extends AbstractSqlDAO implements RecordsDAO {

    @Override
    public void insertar(Record record) {

        String sql = "INSERT INTO records (jugador_id, record, fecha) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, record.getJugador().getId());
            ps.setInt(2, record.getRecord());
            ps.setTimestamp(3, new Timestamp(record.getFecha().getTime()));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Record> consultar(Jugador j) {

        ArrayList<Record> lista = new ArrayList<>();
        String sql = "SELECT id, record, fecha FROM records WHERE jugador_id = ?";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {

            st.setInt(1, j.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Record r = new Record();
                r.setId(rs.getInt("id"));
                r.setJugador(j); // reutilizamos el jugador
                r.setRecord(rs.getInt("record"));
                r.setFecha(rs.getTimestamp("fecha"));

                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void actualizar(Record record) {

        String sql = "UPDATE records SET record = ?, fecha = ? WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, record.getRecord());
            ps.setTimestamp(2, new Timestamp(record.getFecha().getTime()));
            ps.setInt(3, record.getId());

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
