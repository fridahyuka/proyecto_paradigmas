package mx.uamex.fi.paradigmas.model;

import java.sql.Connection;

public abstract class AbstractSqlDAO {
    protected Connection conexion;

    public AbstractSqlDAO() {}

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
