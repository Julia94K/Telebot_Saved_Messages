package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;



public class SaveNoteCommand implements Command{

    private static InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

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
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), ADD_NOTE);
        //обработка команлды происходит в классе UserMessage
    }





}
