package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.Note;

import java.util.List;

public interface NoteService {
    void save(Note note);
    void delete (Note note);
    List<Note> getAllNotes();
}
