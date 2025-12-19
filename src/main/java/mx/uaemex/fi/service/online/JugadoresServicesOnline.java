package mx.uaemex.fi.service.online;

import mx.uaemex.fi.API.PPTAPIPlayersClient;
import mx.uaemex.fi.API.PPTAPIRegisterClient;
import mx.uaemex.fi.API.PPTAPIWhoamiClient;
import mx.uaemex.fi.API.responses.APIPlayerResponse;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.service.JugadoresService;

import java.util.ArrayList;
import java.util.List;

public class JugadoresServicesOnline implements JugadoresService {

    private PPTAPIPlayersClient client;
    private PPTAPIWhoamiClient whoamiClient;
    private PPTAPIRegisterClient registerClient;

    public JugadoresServicesOnline() {

        this.client = new PPTAPIPlayersClient();

        this.whoamiClient = new PPTAPIWhoamiClient();

        this.registerClient = new PPTAPIRegisterClient();
    }

    @Override
    public Jugador actualizarJugador(Jugador j) {
        return null;
    }

    @Override
    public List<Jugador> consultar() {
        return null;
    }

    @Override
    public List<Jugador> consultarUsuario(Jugador j) {
        System.out.println("Consulta del jugador filtro " + j.toString());
        List<Jugador> players = new ArrayList<>();
        if (j.getId() > 0) {
            try {
                players.add(
                        client.getPlayerById(j.getId())
                                .asJugador());
            } catch (Exception e) {
                System.err.println("Imposible consultar el usuario por id " + j.getId() + " " + e.getMessage());
            }
        }

        if (j.getLogin() != null) {

            try {

                List<APIPlayerResponse> response = client.getPlayersByUsername(j.getLogin());

                for (APIPlayerResponse apiPlayerResponse : response) {
                    players.add(apiPlayerResponse.asJugador());

                }
            } catch (Exception e) {
                System.err.println("Imposible consultar el usuario por nombre " + e.getMessage());
            }
        }

        return players;
    }

    @Override
    public boolean eliminarJugador(Jugador j) {
        return false;
    }

    @Override
    public boolean login(Jugador j, String password) {

        boolean result = whoamiClient.testAuthentication(j.getLogin(), password);

        return result;
    }

    @Override
    public Jugador registrarJugador(Jugador j) {
        Jugador jugador = new Jugador();

        try {
            registerClient.register(j.getLogin(), j.getPassword());
            jugador.setActivo(true);
            jugador.setLogin(j.getLogin());
            jugador.setPassword(j.getPassword());

        } catch (Exception e) {
            return null;
        }

        return jugador;
    }

    public PPTAPIPlayersClient getClient() {
        return client;
    }

    public void setClient(PPTAPIPlayersClient client) {
        this.client = client;
    }

    public PPTAPIWhoamiClient getWhoamiClient() {
        return whoamiClient;
    }

    public void setWhoamiClient(PPTAPIWhoamiClient whoamiClient) {
        this.whoamiClient = whoamiClient;
    }

    public PPTAPIRegisterClient getRegisterClient() {
        return registerClient;
    }

    public void setRegisterClient(PPTAPIRegisterClient registerClient) {
        this.registerClient = registerClient;
    }

}
