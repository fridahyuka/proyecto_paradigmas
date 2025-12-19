package mx.uaemex.fi.service.local;

import mx.uaemex.fi.model.JugadoresDAOPsqlImp;
import mx.uaemex.fi.model.RecordsDAOPsqlImp;
import mx.uaemex.fi.service.JugadoresService;
import mx.uaemex.fi.service.RecordsService;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionPostgres {

    private static JugadoresService servicioJugadores;
    private static RecordsService servicioRecords;

    public static void conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/pptls";
            String user = "postgres";
            String password = "123";

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            // ===== Jugadores =====
            JugadoresDAOPsqlImp jugadoresDao = new JugadoresDAOPsqlImp();
            jugadoresDao.setConexion(conn);

            servicioJugadores = new JugadoresServicesLocal();
            ((JugadoresServicesLocal) servicioJugadores).setDao(jugadoresDao);

            // ===== Records =====
            RecordsDAOPsqlImp recordsDao = new RecordsDAOPsqlImp();
            recordsDao.setConexion(conn);

            servicioRecords = new RecordsServiceLocal();
            ((RecordsServiceLocal) servicioRecords).setDao(recordsDao);

            System.out.println("✅ Conectado a PostgreSQL - Base: pptls");

        } catch (Exception e) {
            throw new RuntimeException("No se pudo conectar a PostgreSQL", e);
        }
    }

    public static JugadoresService getServicioJugadores() {
        return servicioJugadores;
    }

    public static RecordsService getServicioRecords() {
        return servicioRecords;
    }
}
