package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.Note;
import com.teleBot.springboot.repository.entity.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Controller
@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
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
