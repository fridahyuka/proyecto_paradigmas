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
            // Cambia la URL de Oracle a PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/pptls";  // Tu base de datos
            String user = "postgres";  // Usuario por defecto
            String password = "123";   // Tu contraseña

            // Registrar driver (opcional en versiones nuevas, pero seguro)
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);

            // Aquí necesitarás un DAO específico para PostgreSQL
            JugadoresDAOPsqlImp dao = new JugadoresDAOPsqlImp();
            dao.setConexion(conn);

            servicio = new JugadoresServicesLocal();
            ((JugadoresServicesLocal) servicio).setDao(dao);

            System.out.println("✅ Conectado a PostgreSQL - Base: pptls");

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
