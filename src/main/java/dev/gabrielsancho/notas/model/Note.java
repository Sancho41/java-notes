package dev.gabrielsancho.notas.model;

import dev.gabrielsancho.notas.dtos.NoteDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String text;
    private Boolean is_public;
    private String color;
    private LocalDate created_at;

    @JoinColumn
    @ManyToOne
    private User user;

    public Note() {
    }

    public Note(NoteDTO note, User loggedUser) {
        this.title = note.getTitle();
        this.text = note.getText();
        this.is_public = note.getIs_public();
        this.color = note.getColor();
        this.created_at = LocalDate.now();
        this.user = loggedUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isIs_public() {
        return is_public;
    }

    public void setIs_public(boolean is_public) {
        this.is_public = is_public;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public void updateFromDTO(NoteDTO noteDTO) {
        this.title = noteDTO.getTitle();
        this.text = noteDTO.getText();
        this.is_public = noteDTO.getIs_public();
        this.color = noteDTO.getColor();
    }
}
