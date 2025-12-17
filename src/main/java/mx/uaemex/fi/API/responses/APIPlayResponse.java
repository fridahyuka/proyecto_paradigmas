package mx.uaemex.fi.API.responses;

import java.util.Date;

public class APIPlayResponse {

    private char resultado;
    private Date fecha;
    private char eleccionJugador;
    private char eleccionAPI;

    public APIPlayResponse() {
    }

    public char getResultado() {
        return resultado;
    }

    public void setResultado(char resultado) {
        this.resultado = resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public char getEleccionJugador() {
        return eleccionJugador;
    }

    public void setEleccionJugador(char eleccionJugador) {
        this.eleccionJugador = eleccionJugador;
    }

    public char getEleccionAPI() {
        return eleccionAPI;
    }

    public void setEleccionAPI(char eleccionAPI) {
        this.eleccionAPI = eleccionAPI;
    }
}
