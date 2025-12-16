package mx.uaemex.fi.API;

import mx.uaemex.fi.session.Movimiento;

public interface PPTAPIClient {
    APIRespuesta jugarContraAPI(Movimiento movimiento);
}
