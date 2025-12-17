package mx.uaemex.fi;

import mx.uaemex.fi.model.JugadoresDAOPsqlImp;
import mx.uaemex.fi.model.RecordsDAOPsqlImp;
import mx.uaemex.fi.service.JugadoresService;
import mx.uaemex.fi.service.JugadoresServicesLocal;
import mx.uaemex.fi.service.RecordsService;
import mx.uaemex.fi.service.RecordsServiceLocal;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static JugadoresService servicioJugadores;
    private static RecordsService servicioRecords;

    public static void conectarLocal() {
        try {

            String url = "jdbc:postgresql://localhost:5432/pptls";  // Tu base de datos
            String user = "postgres";  // Usuario por defecto
            String password = "123";   // Tu contraseña


            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);

            // JugadoresDao
            JugadoresDAOPsqlImp jugadoresdao = new JugadoresDAOPsqlImp();
            jugadoresdao.setConexion(conn);

            servicioJugadores = new JugadoresServicesLocal();

            ((JugadoresServicesLocal) servicioJugadores).setDao(jugadoresdao);

            //RecordsDao
            RecordsDAOPsqlImp recordsdao = new RecordsDAOPsqlImp();
            recordsdao.setConexion(conn);

            servicioRecords = new RecordsServiceLocal();
            ((RecordsServiceLocal)servicioRecords).setDao(recordsdao);

            System.out.println("✅ Conectado a PostgreSQL - Base: pptls");


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo conectar a PostgreSQL: " + e.getMessage());
        }
    }

    public static JugadoresService getServicioJugadores() {
        return servicioJugadores;
    }

    public static RecordsService getServicioRecords() {
        return servicioRecords;
    }
}
