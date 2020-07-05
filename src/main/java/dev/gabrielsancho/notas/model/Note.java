package dev.gabrielsancho.notas.model;

import dev.gabrielsancho.notas.dtos.NoteDTO;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String text;
    private Boolean is_public;
    private String color;

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
        this.user = loggedUser;
    }

//    @ManyToMany(mappedBy = "notesFavorited")
//    private List<User> favorited = new ArrayList<>();

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

//    public List<User> getFavorited() {
//        return favorited;
//    }
//
//    public void setUsers(ArrayList<User> favorited) {
//        this.favorited = favorited;
//    }
}
