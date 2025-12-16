package mx.uaemex.fi.model.data;

public class Juego extends ElementoConID implements Data{

    private String nombre ;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
