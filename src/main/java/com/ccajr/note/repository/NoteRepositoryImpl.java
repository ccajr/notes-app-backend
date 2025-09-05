package com.ccajr.note.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;
import com.ccajr.note.model.Note;

/**
 * In-memory implementation of the NoteRepository interface.
 * Stores notes in a local list and assigns unique IDs using an atomic counter.
 */
@Repository
public class NoteRepositoryImpl implements NoteRepository {
    private final ConcurrentSkipListMap<Long, Note> notes = new ConcurrentSkipListMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    /**
     * Saves a new note and assigns it a unique ID.
     * @param note the note to be saved
     * @return the saved note with an assigned ID
     */
    @Override
    public Note save(Note note) {
        Long id = idCounter.incrementAndGet();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    /**
     * Finds a note by its ID.
     * @param id the ID of the note to retrieve
     * @return an Optional containing the note if found, or empty if not
     */
    @Override
    public Optional<Note> findById(Long id) {
        return Optional.ofNullable(notes.get(id));
    }

    /**
     * Retrieves all notes currently stored in memory.
     * @return a list of all notes
     */
    @Override
    public List<Note> findAll() {
        return new ArrayList<>(notes.values());
    }

    /**
     * Deletes a note by its ID.
     * If the note is not found, throws NoteNotFoundException.
     * @param id the ID of the note to delete
     */
    @Override
    public void deleteById(Long id) {
        notes.remove(id);
    }

    /**
     * Updates an existing note by its ID.
     * If the note is not found, throws NoteNotFoundException.
     * @param id the ID of the note to update
     * @param updatedNote the new note data
     * @return the updated note
     */
    @Override
    public Optional<Note> updateById(Long id, Note newNote) {
        Optional<Note> currNote = findById(id);
        currNote.ifPresent(note -> {
            note.setTitle(newNote.getTitle());
            note.setBody(newNote.getBody());
        });

        return currNote;
    }

    /**
     * Checks whether a note with the given ID exists in memory.
     * @param id the ID of the note to check
     * @return true if the note exists, false if not
     */
    @Override
    public boolean existsById(Long id) {
        return notes.get(id) != null;
    }
}
