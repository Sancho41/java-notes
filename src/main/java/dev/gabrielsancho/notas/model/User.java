package dev.gabrielsancho.notas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

//    @ManyToMany()
//    @JoinTable(
//            name = "note_user",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "note_id"))
//    private List<Note> notesFavorited = new ArrayList<>();

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

//    public void addNotesFavorited(Note note) {
//        notesFavorited.add(note);
//        note.getFavorited().add(this);
//    }
//
//    public void removeNotesFavorited(Note note) {
//        notesFavorited.remove(note);
//        note.getFavorited().remove(this);
//    }
//
//    public List<Note> getNotesFavorited() {
//        return notesFavorited;
//    }
//
//    public void setNotesFavorited(List<Note> notesFavorited) {
//        this.notesFavorited = notesFavorited;
//    }
}
