package mx.uaemex.fi.API.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;

public class APIRecordsResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("player")
    private int player;

    @JsonProperty("created_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updated_at;

    @JsonProperty("record")
    private int record;

    @JsonProperty("is_current_record")
    private boolean is_current_record;

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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public boolean isIs_current_record() {
        return is_current_record;
    }

    public void setIs_current_record(boolean is_current_record) {
        this.is_current_record = is_current_record;
    }

    @Override
    public String toString() {
        return "APIRecordsResponse [id=" + id + ", player=" + player + ", created_at=" + created_at + ", updated_at="
                + updated_at + ", record=" + record + ", is_current_record=" + is_current_record + "]";
    }

}
