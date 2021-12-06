package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Scanner;

public class SaveNoteCommand implements Command{
    private final String NOTE_MSG = "Add new note!";
    private final String ADD_CATEGORY = "Add new category!";
    private final String NOTE_ADDED = "Note was added";
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
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),ADD_CATEGORY);
        Note note = new Note();
        String text = update.getMessage().getText();
//        String chatId = update.getMessage().getChatId().toString();
        Integer updateId = update.getUpdateId();
        note.setCategoryName(text);
        note.setNoteName(text);
        note.setUpdateId(updateId);
        note.setNoteText(text);
        noteInterface.save(note);

        //step 2: User type in category name
//        Scanner in = new Scanner(System.in);
//        String text = in.next(update.getMessage().getText());
//        Note note = new Note();
//        note.setCategoryName(text);
//        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),NOTE_MSG);
//        String chatId = update.getMessage().getChatId().toString();
//        Scanner sc = new Scanner(System.in);
//        String text2 = sc.next(update.getMessage().getText());
//        note.setNoteText(text2);
//        note.setNoteName(text2);
//        note.setChatId(chatId);
//        noteInterface.save(note);
//        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),NOTE_ADDED);



//
//
//
//        Scanner in = new Scanner(System.in);
//        String text = in.next();
//        update.getMessage().getText();
//        String chatId = update.getMessage().getChatId().toString();
//        Note note = new Note();
//        note.setChatId(chatId);//сохраняем chatId в chatId
//        note.setNoteText(text);
//        note.setNoteName(update.getMessage().getText());
//        noteInterface.save(note);

    }
}
