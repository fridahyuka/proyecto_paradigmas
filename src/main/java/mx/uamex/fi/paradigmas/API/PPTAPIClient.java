package mx.uamex.fi.paradigmas.api;

import mx.uamex.fi.paradigmas.session.Movimiento;

public interface PPTAPIClient {
    APIRespuesta jugarContraAPI(Movimiento movimiento);
}
