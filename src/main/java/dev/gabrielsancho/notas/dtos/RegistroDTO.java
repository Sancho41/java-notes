package dev.gabrielsancho.notas.dtos;

public class RegistroDTO extends LoginDTO{

    private String password_confirmation;
    private String name;

    public  RegistroDTO(){
        super();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}
