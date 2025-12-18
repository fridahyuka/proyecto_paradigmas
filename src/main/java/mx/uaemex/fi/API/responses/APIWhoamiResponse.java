package mx.uaemex.fi.API.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APIWhoamiResponse {
    @JsonProperty("username")
    private String username;
    @JsonProperty("detail")
    private String detail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "APIWhoamiResponse [username=" + username + ", detail=" + detail + "]";
    }

}
