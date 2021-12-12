package com.teleBot.springboot.commands.categories;

import com.teleBot.springboot.commands.Command;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class Education implements Command {
    public final String EDU_MSG = "Here are all your saved messages:";
    private final SendMessageInterface sendMessageInterface;
    private final NoteInterface noteInterface;


    public Education(SendMessageInterface sendMessageInterface, NoteInterface noteInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.noteInterface = noteInterface;
    }

    @Override
    public void executeCommand(Update update){
        List <Note> notes = noteInterface.getAllNotes();
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),EDU_MSG);
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),notes.toString());


    }
}
