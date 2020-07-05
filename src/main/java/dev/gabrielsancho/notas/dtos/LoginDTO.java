package dev.gabrielsancho.notas.dtos;

public class LoginDTO {
    private String login;
    private String senha;
    private String token;

    public LoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public LoginDTO(String login, String senha, String token) {
        this.login = login;
        this.senha = senha;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
