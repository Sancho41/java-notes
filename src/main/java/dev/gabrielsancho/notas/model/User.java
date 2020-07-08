package dev.gabrielsancho.notas.model;

import dev.gabrielsancho.notas.dtos.RegisterUserDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Column(unique=true)
    private String email;

    private String password;

    public User() {

    }

    public User(RegisterUserDTO registerUserDTO) {
        this.name = registerUserDTO.getName();
        this.email = registerUserDTO.getEmail();
        this.password = registerUserDTO.getPassword();
    }

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "favorited",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "note_id"))
    private List<Note> notesFavorited = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void toggleFavorite(Note note) {
        if (this.notesFavorited.contains(note))
            this.notesFavorited.remove(note);
        else
            this.notesFavorited.add(note);
    }
}
