package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

//дописать

public class GetNotesForCategoryCommand implements Command{

//    public static final String START_MSG = "Hello let's start!";
    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;
    private final NoteInterface noteInterface;

    public GetNotesForCategoryCommand(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface,
                                      NoteInterface noteInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.categoryInterface = categoryInterface;
        this.noteInterface = noteInterface;
    }
    //тут метод должен по названию категории получать список созданных к ней записей
    @Override
    public void executeCommand(Update update){
////        List<Note> notes = noteInterface.findByCategoryName(update.getMessage().getText());
//        List<Note> notes = noteInterface.findByCategoryName();
//        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),notes.toString());

    }
}
