package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Scanner;

public class SaveNoteCommand implements Command{
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private final String NOTE_MSG = "Add new note!";
    private final String NOTE_ADDED = "Note was added";
    String ADD_NOTE = "Select category:";
    private final SendMessageInterface sendMessageInterface;
    private final NoteInterface noteInterface;
    private final CategoryInterface categoryInterface;

    public SaveNoteCommand(SendMessageInterface sendMessageInterface, NoteInterface noteInterface,
                           CategoryInterface categoryInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.noteInterface = noteInterface;
        this.categoryInterface = categoryInterface;
    }


    @Override
    public void executeCommand(Update update){



        //step 1: TG bot sends message "Please enter category name"
        //step 2: TG bot sends message "Please enter note"
        //step 3: note will be saved

        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), ADD_NOTE);

        Note note = new Note();
        String text = update.getMessage().getText();
//        String chatId = update.getMessage().getChatId().toString();
        Integer updateId = update.getUpdateId();
        Long  chatId = update.getMessage().getChatId();
        note.setCategoryName("Education");
        note.setUpdateId(updateId);
        note.setChatId(chatId);
        note.setNoteText("https://spring.io/guides#topical-guides");
        noteInterface.save(note);

    }





}
