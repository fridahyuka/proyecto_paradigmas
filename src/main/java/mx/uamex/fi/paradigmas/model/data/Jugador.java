package mx.uamex.fi.paradigmas.data;

public class Jugador implements Data {
    private String login;
    private String password;
    private String correo;
    private String atributo;

    public Jugador() {}

    public Jugador(String login, String password, String correo, String atributo) {
        this.login = login;
        this.password = password;
        this.correo = correo;
        this.atributo = atributo;
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

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", correo='" + correo + '\'' +
                ", atributo='" + atributo + '\'' +
                '}';
    }
}
