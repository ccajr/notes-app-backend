package com.ccajr.note.repository;

import java.util.List;
import java.util.Optional;

import com.ccajr.note.model.Note;

public interface NoteRepository {

    public Note save(Note note);

    public Optional<Note> findById(Long id);

    public List<Note> findAll();

    public void deleteById(Long id);

    public Optional<Note> updateById(Long id, Note newNote);

    public boolean existsById(Long id);
}
