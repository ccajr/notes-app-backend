package com.ccajr.note.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ccajr.note.dto.NoteRequestDto;
import com.ccajr.note.dto.NoteResponseDto;
import com.ccajr.note.exception.NoteNotFoundException;
import com.ccajr.note.mapper.NoteMapper;
import com.ccajr.note.model.Note;
import com.ccajr.note.repository.NoteRepositoryImpl;

/**
 * Business logic implementation for managing notes.
 */
@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepositoryImpl repository;
    private final NoteMapper mapper;

    public NoteServiceImpl(NoteRepositoryImpl repository, NoteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Retrieves all notes.
     * @return list of notes
     */
    @Override
    public List<NoteResponseDto> getAllNotes() {
        return this.repository.findAll().stream()
            .map(mapper::toNoteResponse)
            .toList();
    }

    /**
     * Retrieves a note by ID.
     * @param id the ID of the note
     * @return the found note
     * @throws NoteNotFoundException if no note exists with the specified ID
     */
    @Override
    public NoteResponseDto getNoteById(Long id) {
        return this.repository.findById(id).map(mapper::toNoteResponse).orElseThrow(() -> new NoteNotFoundException(id));
    }

    /**
     * Creates a new note and assigns a unique ID.
     * @param noteDto the note to create
     * @return the created note
     */
    @Override
    public NoteResponseDto createNote(NoteRequestDto noteDto) {
        Note note = mapper.toNote(noteDto);
        Note savedNote = this.repository.save(note);
        return mapper.toNoteResponse(savedNote);
    }

    /**
     * Updates an existing note.
     * @param id the ID of the note to update
     * @param updatedNoteDto the new note data
     * @return the updated note
     * @throws NoteNotFoundException if no note exists with the specified ID
     */
    @Override
    public NoteResponseDto updateNote(Long id, NoteRequestDto updatedNoteDto) {
        Note currNote = this.repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        Note updatedNote = mapper.toNote(updatedNoteDto);
        currNote.setTitle(updatedNote.getTitle());
        currNote.setBody(updatedNote.getBody());

        return this.repository.updateById(id, updatedNote).map(mapper::toNoteResponse).orElseThrow(() -> new NoteNotFoundException(id));
    }

    /**
     * Deletes a note by ID.
     * @param id the ID of the note to delete
     */
    @Override
    public void deleteNote(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new NoteNotFoundException(id);
        }
    }
}
