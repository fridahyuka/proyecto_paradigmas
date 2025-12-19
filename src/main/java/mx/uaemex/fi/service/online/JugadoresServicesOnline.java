package mx.uaemex.fi.service.online;

import mx.uaemex.fi.API.PPTAPIPlayersClient;
import mx.uaemex.fi.API.responses.APIPlayerResponse;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.service.JugadoresService;

import java.util.ArrayList;
import java.util.List;

public class JugadoresServicesOnline implements JugadoresService {

    PPTAPIPlayersClient client;

    public JugadoresServicesOnline() {

        this.client = new PPTAPIPlayersClient();
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

        List<Jugador> players = new ArrayList<>();
        Integer id = j.getId();
        if (id != null) {
            try {
                players.add(
                        client.getPlayerById(j.getId())
                                .asJugador());
            } catch (Exception e) {
                System.err.println("Imposible hacer la petición");
            }
        }

        if (j.getLogin() != null) {

            try {

                List<APIPlayerResponse> response = client.getPlayersByUsername(j.getLogin());

                for (APIPlayerResponse apiPlayerResponse : response) {
                    players.add(apiPlayerResponse.asJugador());

                }
            } catch (Exception e) {
                System.err.println("Imposible hacer la petición");
            }
        }

        return players;
    }

    @Override
    public boolean eliminarJugador(Jugador j) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean login(Jugador j, String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Jugador registrarJugador(Jugador j) {
        // TODO Auto-generated method stub
        return null;
    }

    public PPTAPIPlayersClient getClient() {
        return client;
    }

    public void setClient(PPTAPIPlayersClient client) {
        this.client = client;
    }

}
