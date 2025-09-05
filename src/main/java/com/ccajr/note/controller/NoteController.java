package com.ccajr.note.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ccajr.note.dto.NoteRequestDto;
import com.ccajr.note.dto.NoteResponseDto;
import com.ccajr.note.service.NoteService;

import jakarta.validation.Valid;

/**
 * REST controller for managing notes.
 */
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    /**
     * Creates a new note.
     * @param note the note to be created
     * @return the created note with HTTP 200 OK
     */
    @PostMapping
    public ResponseEntity<NoteResponseDto> createNote(@Valid @RequestBody NoteRequestDto note) {
        NoteResponseDto createdNote = service.createNote(note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    /**
     * Retrieves all notes.
     * @return list of notes with HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> getAllNotes() {
        List<NoteResponseDto> notes = service.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    /**
     * Retrieves a note by its ID.
     * @param id the ID of the note
     * @return the found note or HTTP 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNoteById(id));
    }

    /**
     * Updates an existing note.
     * @param id the ID of the note to update
     * @param note the updated note data
     * @return the updated note with HTTP 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDto> updateNote(@PathVariable Long id, @Valid @RequestBody NoteRequestDto note) {
        return ResponseEntity.ok(service.updateNote(id, note));
    }

    /**
     * Deletes a note by its ID.
     * @param id the ID of the note to delete
     * @return HTTP 204 No Content or HTTP 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
