package mx.uaemex.fi.API.responses;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class APIMatchesResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("player")
    private int player;

    @JsonProperty("player_choice")
    private String player_choice;

    @JsonProperty("machine_choice")
    private String machine_choice;

    @JsonProperty("result")
    private char result;

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

    public char getResult() {
        return result;
    }

    public void setResult(char result) {
        this.result = result;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "APIMatchesResponse [id=" + id + ", player=" + player + ", player_choice=" + player_choice
                + ", machine_choice=" + machine_choice + ", result=" + result + ", created_at=" + created_at + "]";
    }

}
