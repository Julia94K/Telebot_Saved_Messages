package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.Category;
import com.teleBot.springboot.repository.entity.Note;
import com.teleBot.springboot.repository.entity.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteFunction implements NoteInterface{
    private final NoteRepository noteRepository;

    @Autowired
    public NoteFunction(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void save(Note note){
        noteRepository.save(note);
    }

    @Override
    public void delete (Note note){
        noteRepository.delete(note);
    }

    @Override
    public List<Note>getAllNotes(){
        return noteRepository.findAll();

    }


    public List<Note>findByCategoryName(String categoryName){
        return noteRepository.findAllByCategoryName(categoryName);

    }



}
