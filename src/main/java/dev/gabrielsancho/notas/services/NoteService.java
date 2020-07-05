package dev.gabrielsancho.notas.services;

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
        EntityManager em = createEntityManager();
        String jpql = "Select n from Note n where n.is_public = true";
        TypedQuery<Note> query = em.createQuery(jpql, Note.class);
        query.setMaxResults(10);
        List<Note> notes = query.getResultList();
        em.close();
        return notes;
    }



    public Note create(Note note, User loggedUser) {
        EntityManager em = createEntityManager();
        note.setUser(loggedUser);
        em.getTransaction().begin();
        em.persist(note);
        em.getTransaction().commit();
        em.close();
        return note;
    }

    public Note getNote(Long id) {
        EntityManager em = createEntityManager();
        return em.find(Note.class, id);
    }

    public Note updateNote(Note note, User loggedUser) throws Exception {
        EntityManager em = createEntityManager();

        Note testNote = em.find(Note.class, note.getId());

        if (testNote.getUser().equals(loggedUser)) {

            // Tornar user imutável
            note.setUser(loggedUser);

            em.getTransaction().begin();
            em.merge(note);
            em.getTransaction().commit();
            em.close();
            return note;
        }

        throw new Exception("not authorized");
    }

    public void deleteNote(Long id, User loggedUser) throws Exception {
        EntityManager em = createEntityManager();

        Note note = em.find(Note.class, id);

        if (note.getUser().equals(loggedUser))
        {
            em.getTransaction().begin();
            em.remove(note);
            em.getTransaction().commit();
            em.close();
            return;
        }
        throw new Exception("not authorized");
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