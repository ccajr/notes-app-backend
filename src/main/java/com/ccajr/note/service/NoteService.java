package com.ccajr.note.service;

import java.util.List;

import com.ccajr.note.model.Note;

public interface NoteService {

    public List<Note> getAllNotes();

    public Note getNoteById(Long id);

    public Note createNote(Note note);

    public Note updateNote(Long id, Note updatedNote);

    public void deleteNote(Long id);
}
