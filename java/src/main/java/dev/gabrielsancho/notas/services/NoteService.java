package dev.gabrielsancho.notas.services;

import dev.gabrielsancho.notas.dtos.NoteDTO;
import dev.gabrielsancho.notas.dtos.NoteListDTO;
import dev.gabrielsancho.notas.model.Note;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.persistence.NoteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class NoteService {

    public final NoteDAO dao = new NoteDAO();

    public NoteListDTO publicNotes(Long page, Long perPage) {
        return dao.getPublicNotes(page, perPage);
    }

    public NoteListDTO publicNotes(User user, Long page, Long perPage) {
        return dao.getPublicNotes(user, page, perPage);
    }

    public Note create(NoteDTO note, User loggedUser) throws Exception {
        return dao.create(new Note(note, loggedUser));
    }

    public NoteDTO getNoteById(Long id) {
        return dao.getNoteById(id);
    }

    public NoteDTO getNoteById(Long id, User loggedUser) {
        return dao.getNoteById(id, loggedUser);
    }

    public NoteDTO updateNote(NoteDTO note, User loggedUser) throws Exception {
        return dao.update(note, loggedUser);
    }

    public void deleteNote(Long id, User loggedUser) throws Exception {
        dao.delete(id, loggedUser);
    }

    public List<NoteDTO> userNotes(User loggedUser) {
        return dao.getUserNotes(loggedUser);
    }

    public NoteDTO favorite(User loggedUser, Long id) throws Exception {
        return dao.favoriteNote(id, loggedUser);
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