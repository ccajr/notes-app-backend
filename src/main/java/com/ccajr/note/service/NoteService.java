package com.ccajr.note.service;

import java.util.List;
import com.ccajr.note.dto.NoteRequestDto;
import com.ccajr.note.dto.NoteResponseDto;

/**
 * Service interface for note-related operations.
 */
public interface NoteService {

    public List<NoteResponseDto> getAllNotes();

    public NoteResponseDto getNoteById(Long id);

    public NoteResponseDto createNote(NoteRequestDto note);

    public NoteResponseDto updateNote(Long id, NoteRequestDto updatedNote);

    public void deleteNote(Long id);
}
