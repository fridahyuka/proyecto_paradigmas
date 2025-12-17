package mx.uaemex.fi;

import mx.uaemex.fi.model.JugadoresDAOPsqlImp;
import mx.uaemex.fi.service.JugadoresService;
import mx.uaemex.fi.service.JugadoresServicesLocal;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static JugadoresService servicio;

    public static JugadoresService conectarLocal() {
        try {

            String url = "jdbc:postgresql://localhost:5432/pptls";
            String user = "postgres";
            String password = "123";

            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);

            JugadoresDAOPsqlImp dao = new JugadoresDAOPsqlImp();
            dao.setConexion(conn);

            servicio = new JugadoresServicesLocal();
            ((JugadoresServicesLocal) servicio).setDao(dao);

            return servicio;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo conectar a PostgreSQL: " + e.getMessage());
        }
    }

    public static JugadoresService getJugadoresService() {
        return servicio;
    }
}
