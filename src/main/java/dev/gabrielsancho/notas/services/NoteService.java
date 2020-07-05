package dev.gabrielsancho.notas.services;

import dev.gabrielsancho.notas.dtos.NoteDTO;
import dev.gabrielsancho.notas.model.Note;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.persistence.NoteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class NoteService {

    public final NoteDAO dao = new NoteDAO();


    private EntityManager createEntityManager () {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");
        return emf.createEntityManager();
    }

    public List<Note> publicNotes() {
        return dao.getPublicNotes();
    }

    public Note create(NoteDTO note, User loggedUser) throws Exception {
        return dao.create(new Note(note, loggedUser));
    }

    public Note getNote(Long id) {
        return dao.getNoteById(id);
    }

    public Note updateNote(Note note, User loggedUser) throws Exception {
        return dao.update(note, loggedUser);
    }

    public void deleteNote(Long id, User loggedUser) throws Exception {
        dao.delete(id, loggedUser);
    }

    public List<Note> userNotes(User loggedUser) {
        return dao.getUserNotes(loggedUser);
    }

    public Note favorite(User loggedUser, Note note) throws Exception {
        if (note.isIs_public() || note.getUser().equals(loggedUser))
            return note;

        throw new Exception("not authorized");
    }

    public ArrayList<Note> favorites(User user) {
        ArrayList<Note> notes = new ArrayList<Note>();

        for (int i = 0; i < 5; i++) {
            notes.add(new Note());
            notes.get(i).setColor(String.format("color: %d", i));
            notes.get(i).setIs_public(true);
            notes.get(i).setText(String.format("texto da nota %d", i + 1));
            notes.get(i).setTitle(String.format("Título da nota %d", i + 1));
            notes.get(i).setUser(new User());
        }

        // Pega as notas favoritadas pelo usuário

        return notes;
    }
}