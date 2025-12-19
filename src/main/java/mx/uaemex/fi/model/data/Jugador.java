package mx.uaemex.fi.model.data;

public class Jugador extends ElementoConID implements Data {
    private String login;
    private String password;
    private String correo;
    private boolean activo;

    public Jugador() {
        this.setId(0);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Jugador [id=" + id + ", login=" + login + ", password=" + password + ", correo=" + correo + ", activo="
                + activo + "]";
    }

}
