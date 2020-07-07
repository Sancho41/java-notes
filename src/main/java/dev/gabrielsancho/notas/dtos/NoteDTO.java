package dev.gabrielsancho.notas.dtos;

import dev.gabrielsancho.notas.model.Note;
import dev.gabrielsancho.notas.model.User;

public class NoteDTO {
    private Long id;
    private String title;
    private String text;
    private Boolean is_public;
    private String color;
    private User user;
    private Boolean is_favorited;

    public NoteDTO() {
    }

    public NoteDTO(Note note, Boolean is_favorited) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.text = note.getText();
        this.is_public = note.isIs_public();
        this.color = note.getColor();
        this.user = note.getUser();
        this.is_favorited = is_favorited;
    }

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.text = note.getText();
        this.is_public = note.isIs_public();
        this.color = note.getColor();
        this.user = note.getUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIs_public() {
        return is_public;
    }

    public void setIs_public(Boolean is_public) {
        this.is_public = is_public;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIs_favorited() {
        return is_favorited;
    }

    public void setIs_favorited(Boolean is_favorited) {
        this.is_favorited = is_favorited;
    }
}