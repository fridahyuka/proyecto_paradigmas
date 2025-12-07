package mx.uamex.fi.paradigmas.model;

import mx.uamex.fi.paradigmas.data.Jugador;

import java.sql.*;
import java.util.ArrayList;

public class JugadoresDAOPsqlImp extends AbstractSqlDAO implements JugadoresDAO {

    @Override
    public void insertar(Jugador jugador) {
        String sql = "INSERT INTO jugadores(login, password, correo, atributo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, jugador.getLogin());
            ps.setString(2, jugador.getPassword());
            ps.setString(3, jugador.getCorreo());
            ps.setString(4, jugador.getAtributo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Jugador> consultar() {
        ArrayList<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";

        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Jugador j = new Jugador(
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("correo"),
                        rs.getString("atributo")
                );
                lista.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void actualizar(Jugador jugador) {
        String sql = "UPDATE jugadores SET password=?, correo=?, atributo=? WHERE login=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, jugador.getPassword());
            ps.setString(2, jugador.getCorreo());
            ps.setString(3, jugador.getAtributo());
            ps.setString(4, jugador.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrar(Jugador jugador) {
        String sql = "DELETE FROM jugadores WHERE login=?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, jugador.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
