package mx.uaemex.fi.model;

import mx.uaemex.fi.model.data.Jugador;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementaci&oacute;n del DAO de jugadores utilizando una base de datos
 * relacional.
 */
public class JugadoresDAOPsqlImp extends AbstractSqlDAO implements JugadoresDAO {
    /**
     * <p>
     * M&eacute;todo para insertar un jugador en la base de datos.
     * No pueden repetirse ni el nombre de usuario (login) ni
     * el correo.
     * </p>
     * <p>
     * Los campos login, password y correo son obligatorios,
     * mientras que el id ser&aacute; generado por la base.
     * </p>
     * 
     * @param jugador Objetos con la informaci&pacute;n de creaci&oacute;n
     * @return Jugador inseertado.
     */
    @Override
    public Jugador insertar(Jugador jugador) {
        String sql = "INSERT INTO jugadores (login, password, correo, activo) VALUES (?, ?, ?, ?)";

        // Usar RETURN_GENERATED_KEYS para obtener el ID directamente
        try (PreparedStatement stmt = this.conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String login = jugador.getLogin();
            String password = jugador.getPassword();
            String correo = jugador.getCorreo();

            if (login == null || password == null || correo == null) {
                throw new RuntimeException("Información insuficiente para registrar jugador");
            }

            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, correo);
            stmt.setString(4, "true");

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                // Obtener el ID generado por PostgreSQL
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    jugador.setId(generatedKeys.getInt(1));
                }
                return jugador;
            } else {
                throw new RuntimeException("No se pudo insertar el jugador");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar jugador: " + e.getMessage(), e);
        }
    }

    /**
     * <p>
     * M&eacute;todo para consultar jugadores utilizando
     * la estrategia de QBE (Query By Example) en la que
     * la b&uacute;squeda se realiza utilizando el par&aacute;metro
     * como ejemplo de lo que se debe buscar.
     * </p>
     * <p>
     * <b>Nota:</b> Si el Jugador pasado como p&aacute;rametro no
     * tiene ning&uacute;n atributo establecido el m&eacute;todo
     * se comporta como "consultar todos".
     * </p>
     * 
     * @param jugador Objeto que se&ntilde;ala los criterios y
     *                valores de b&uacute;squeda.
     * @return Colecci&oacute;n de Jugadores que cumplen con los
     *         criterios de b&uacute;squeda. En caso de no encontrar ninguno
     *         devolver&aacute; una lista vac&iacute;a.
     * @throws RuntimeException en caso de error.
     */
    @Override
    public ArrayList<Jugador> consultar(Jugador jugador) {
        ArrayList<Jugador> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM jugadores");
        ArrayList<Object> params = new ArrayList<>();

        boolean whereAdded = false;

        // === FILTRO POR ID ===
        if (jugador.getId() > 0) {
            sql.append(whereAdded ? " AND" : " WHERE");
            sql.append(" id = ?");
            params.add(jugador.getId());
            whereAdded = true;
        }

        // === FILTRO POR LOGIN ===
        if (jugador.getLogin() != null && !jugador.getLogin().isBlank()) {
            sql.append(whereAdded ? " AND" : " WHERE");
            sql.append(" login = ?");
            params.add(jugador.getLogin().trim());
            whereAdded = true;
        }

        // === FILTRO POR PASSWORD Solo si REALMENTE se está buscando por password ===
        if (jugador.getPassword() != null && !jugador.getPassword().isBlank()) {
            sql.append(whereAdded ? " AND" : " WHERE");
            sql.append(" password = ?");
            params.add(jugador.getPassword());
            whereAdded = true;
        }

        // === FILTRO POR CORREO ===
        if (jugador.getCorreo() != null && !jugador.getCorreo().isBlank()) {
            sql.append(whereAdded ? " AND" : " WHERE");
            sql.append(" correo = ?");
            params.add(jugador.getCorreo());
            whereAdded = true;
        }

        // === FILTRO POR ACTIVO ===
        // Sólo si el usuario explícitamente lo pide (para login NO se filtra aquí)
        if (jugador.isActivo()) {
            sql.append(whereAdded ? " AND" : " WHERE");
            sql.append(" activo = 'true'");
            whereAdded = true;
        }

        try (PreparedStatement stmt = this.conexion.prepareStatement(sql.toString())) {

            // Cargar parámetros
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setId(rs.getInt("id"));
                j.setLogin(rs.getString("login"));
                j.setPassword(rs.getString("password"));
                j.setCorreo(rs.getString("correo"));

                // activo es VARCHAR 'true' / 'false'
                j.setActivo(
                        rs.getString("activo")
                                .equalsIgnoreCase("true"));

                lista.add(j);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar jugadores", e);
        }

        return lista;
    }

    /**
     * Consultar todos los jugadores activos.
     * 
     * @return Colecci&oacute;n de jugadores (activos y no activos)
     */
    @Override
    public ArrayList<Jugador> consultar() {
        Statement stmt;
        String sql;
        ResultSet resultado;

        try {
            sql = "SELECT * FROM jugadores where activo='true'";
            stmt = this.conexion.createStatement();
            resultado = stmt.executeQuery(sql);
            ArrayList<Jugador> jugadores = new ArrayList<>();
            while (resultado.next()) {
                Jugador j = new Jugador();
                j.setId(resultado.getInt(1)); // La primera columna es el Id
                j.setLogin(resultado.getString("login"));
                j.setCorreo(resultado.getString("correo"));
                j.setPassword(resultado.getString("password"));
                j.setActivo(resultado.getBoolean("activo"));
                jugadores.add(j);
            }
            return jugadores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>
     * Utilizaremos el nombre de usuario (login) como
     * identificador de los registros, lo que implica que
     * puede cambiar el correo o el password o bien recativar
     * a un usuario.
     * </p>
     * <p>
     * <b>Nota:</b> no se puede desactivar un jugador porque eso
     * implica eliminarlo
     * </p>
     * 
     * @param jugador Objeto con la informaci&oacute;n
     *                para la actualizaci&oacute;n.
     */
    @Override
    public void actualizar(Jugador jugador) {
        if (jugador.getLogin() == null || jugador.getLogin().isBlank()) {
            throw new RuntimeException("Login es requerido para actualizar");
        }

        StringBuilder sql = new StringBuilder("UPDATE jugadores SET ");
        ArrayList<Object> valores = new ArrayList<>();
        int numColumnas = 0;

        // Password
        String password = jugador.getPassword();
        if (password != null && !password.isBlank()) {
            sql.append("password = ?");
            valores.add(password);
            numColumnas++;
        }

        // Correo
        String correo = jugador.getCorreo();
        if (correo != null && !correo.isBlank()) {
            if (numColumnas > 0) {
                sql.append(", ");
            }
            sql.append("correo = ?");
            valores.add(correo);
            numColumnas++;
        }

        // Activo - SIEMPRE se debe actualizar
        if (numColumnas > 0) {
            sql.append(", ");
        }
        sql.append("activo = ?");
        valores.add(jugador.isActivo() ? "true" : "false");

        // WHERE clause
        sql.append(" WHERE login = ?");
        valores.add(jugador.getLogin());

        try (PreparedStatement pstmt = this.conexion.prepareStatement(sql.toString())) {

            // Cargar TODOS los parámetros
            for (int i = 0; i < valores.size(); i++) {
                pstmt.setObject(i + 1, valores.get(i));
            }

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new RuntimeException("No se encontró el jugador con login: " + jugador.getLogin());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar jugador: " + e.getMessage(), e);
        }
    }

    /**
     * Eliminaci&oacute;n de un jugador. La eliminaci&oacute;n es l&oacute;gica
     * y no f&iacute;sica.
     * 
     * @param jugador a eliminar
     */
    @Override
    public void eliminar(Jugador jugador) {
        if (jugador.getLogin() == null || jugador.getLogin().isBlank()) {
            throw new RuntimeException("Login es requerido para eliminar");
        }

        String sql = "UPDATE jugadores SET activo = 'false' WHERE login = ?";

        try (PreparedStatement stmt = this.conexion.prepareStatement(sql)) {
            stmt.setString(1, jugador.getLogin());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new RuntimeException("No se encontró el jugador con login: " + jugador.getLogin());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar jugador: " + e.getMessage(), e);
        }
    }

    @Override
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
