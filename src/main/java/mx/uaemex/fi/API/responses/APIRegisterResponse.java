package mx.uaemex.fi.API.responses;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APIRegisterResponse {

    @JsonProperty("username")
    private String[] username;
    @JsonProperty("password")
    private String[] password;

    public String[] getUsername() {
        return username;
    }

    public void setUsername(String[] username) {
        this.username = username;
    }

    public String[] getPassword() {
        return password;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "APIRegisterResponse [username=" + Arrays.toString(username) + ", password=" + Arrays.toString(password)
                + "]";
    }

}
