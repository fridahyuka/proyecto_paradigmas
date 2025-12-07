package mx.uamex.fi.paradigmas.api;

import mx.uamex.fi.paradigmas.session.Movimiento;
import java.util.Date;

public class PPTAPIHTTPClient implements PPTAPIClient {

    @Override
    public APIRespuesta jugarContraAPI(Movimiento movimiento) {
        APIRespuesta resp = new APIRespuesta();
        resp.setFecha(new Date());
        resp.setEleccionJugador(movimiento.getSimbolo());
        resp.setEleccionAPI('P'); 
        resp.setResultado('G');   // G = Ganar, P = Perder, E = Empate
        return resp;
    }
}
