package com.ccajr.note.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccajr.note.exception.NoteNotFoundException;
import com.ccajr.note.model.Note;
import com.ccajr.note.repository.NoteRepositoryImpl;

@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepositoryImpl repository;

    public NoteServiceImpl(NoteRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<Note> getAllNotes() {
        return this.repository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public Note createNote(Note note) {
        return this.repository.save(note);
    }

    @Override
    public Note updateNote(Long id, Note updatedNote) {
        Note currNote = this.repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        currNote.setTitle(updatedNote.getTitle());
        currNote.setBody(updatedNote.getBody());

        return this.repository.updateById(id, updatedNote).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @Override
    public void deleteNote(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new NoteNotFoundException(id);
        }
    }
}
