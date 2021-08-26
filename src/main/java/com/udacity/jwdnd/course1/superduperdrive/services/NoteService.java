package com.udacity.jwdnd.course1.superduperdrive.services;

import com.udacity.jwdnd.course1.superduperdrive.mapper.NoteMapper;
import com.udacity.jwdnd.course1.superduperdrive.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    public void addNote(Note note) {
        this.noteMapper.insertNote(note);
    }

    public Note getNote(Note note) {
        return this.noteMapper.findNote(note);
    }

    public Note getNoteById(Long noteId) {
        return this.noteMapper.findNoteById(noteId);
    }

    public List<Note> getNotes(Long userId) {
        return this.noteMapper.findAllNotes(userId);
    }

    public void deleteNote(Long noteId) {
        this.noteMapper.deleteNote(noteId);
    }

    public void updateNote(Note note) {
        this.noteMapper.updateNote(note);
    }
}
