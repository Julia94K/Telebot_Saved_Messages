package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaveNoteController {

    @Autowired
    NoteRepository noteRepository;

//    @GetMapping
//
}
