package mx.uaemex.fi.API.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import mx.uaemex.fi.model.data.Jugador;

public class APIPlayerResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("is_superuser")
    private Boolean is_superuser;

    @JsonProperty("username")
    private String username;

    @JsonProperty("total_wins")
    private int total_wins;

    @JsonProperty("total_losses")
    private int total_losses;

    @JsonProperty("total_games")
    private int total_games;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(Boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotal_wins() {
        return total_wins;
    }

    public void setTotal_wins(int total_wins) {
        this.total_wins = total_wins;
    }

    public int getTotal_losses() {
        return total_losses;
    }

    public void setTotal_losses(int total_losses) {
        this.total_losses = total_losses;
    }

    public int getTotal_games() {
        return total_games;
    }

    public void setTotal_games(int total_games) {
        this.total_games = total_games;
    }

    public Jugador asJugador() {

        Jugador jugador = new Jugador();
        jugador.setActivo(true);
        jugador.setId(id);
        jugador.setLogin(username);
        return jugador;

    }

    @Override
    public String toString() {
        return "APIPlayerResponse [id=" + id + ", is_superuser=" + is_superuser + ", username=" + username
                + ", total_wins=" + total_wins + ", total_losses=" + total_losses + ", total_games=" + total_games
                + "]";
    }

}
