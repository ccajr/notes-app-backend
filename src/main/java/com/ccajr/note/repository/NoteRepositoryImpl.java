package com.ccajr.note.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.ccajr.note.model.Note;

@Repository
public class NoteRepositoryImpl implements NoteRepository {
    private final ConcurrentSkipListMap<Long, Note> notes = new ConcurrentSkipListMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public Note save(Note note) {
        Long id = idCounter.incrementAndGet();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    @Override
    public Optional<Note> findById(Long id) {
        return Optional.ofNullable(notes.get(id));
    }

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(notes.values());
    }

    @Override
    public void deleteById(Long id) {
        notes.remove(id);
    }

    @Override
    public Optional<Note> updateById(Long id, Note newNote) {
        Optional<Note> currNote = findById(id);
        currNote.ifPresent(note -> {
            note.setTitle(newNote.getTitle());
            note.setBody(newNote.getBody());
        });

        return currNote;
    }

    @Override
    public boolean existsById(Long id) {
        return notes.get(id) != null;
    }
}
