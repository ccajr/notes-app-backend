package com.ccajr.note.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ccajr.note.dto.NoteRequestDto;
import com.ccajr.note.dto.NoteResponseDto;
import com.ccajr.note.model.Note;

@Component
public class NoteMapper {
    private final ModelMapper modelMapper;

    public NoteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Note toNote(NoteRequestDto request) {
        return modelMapper.map(request, Note.class);
    }

    public NoteResponseDto toNoteResponse(Note note) {
        return modelMapper.map(note, NoteResponseDto.class);
    }
}
