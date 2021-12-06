package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.Note;

import java.util.List;

public interface NoteInterface {
    void save(Note note);
    List<Note>findByCategoryName(String categoryName);
}
