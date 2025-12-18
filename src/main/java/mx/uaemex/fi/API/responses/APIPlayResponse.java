package mx.uaemex.fi.API.responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import mx.uaemex.fi.game.Movimiento;
import mx.uaemex.fi.game.Resultado;
import mx.uaemex.fi.model.data.Jugador;
import mx.uaemex.fi.session.Partida;

public class APIPlayResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("player")
    private int player;

    @JsonProperty("player_choice")
    private String player_choice;

    @JsonProperty("machine_choice")
    private String machine_choice;

    @JsonProperty("result")
    private String result;

    @JsonProperty("created_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public String getPlayer_choice() {
        return player_choice;
    }

    public void setPlayer_choice(String player_choice) {
        this.player_choice = player_choice;
    }

    public String getMachine_choice() {
        return machine_choice;
    }

    public void setMachine_choice(String machine_choice) {
        this.machine_choice = machine_choice;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Partida asPartida() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador();
        jugador.setId(player);
        partida.setResultado(Resultado.fromSymbol(result.charAt(0)));
        partida.setMovJugador(Movimiento.fromSymbol(player_choice));
        partida.setMovOponente(Movimiento.fromSymbol(machine_choice));
        partida.setJugador(jugador);
        return partida;
    }

    @Override
    public String toString() {
        return "APIPlayResponse [id=" + id + ", player=" + player + ", player_choice=" + player_choice
                + ", machine_choice=" + machine_choice + ", result=" + result + ", created_at=" + created_at + "]";
    }

}
