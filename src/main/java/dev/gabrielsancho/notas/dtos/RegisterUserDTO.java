package dev.gabrielsancho.notas.dtos;

public class RegisterUserDTO {
    private String email;
    private String password;
    private String password_confirmation;
    private String name;

    public Boolean passwordMatch() {
        return this.password.equals(this.password_confirmation);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}