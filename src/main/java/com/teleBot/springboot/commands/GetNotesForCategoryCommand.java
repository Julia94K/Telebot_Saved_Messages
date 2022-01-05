package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

//дописать

public class GetNotesForCategoryCommand implements Command{

//    public static final String START_MSG = "Hello let's start!";
    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;
    private final NoteInterface noteInterface;
    public final static String INFO_NOTES = "Notes in this collection:";


    public GetNotesForCategoryCommand(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface,
                                      NoteInterface noteInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.categoryInterface = categoryInterface;
        this.noteInterface = noteInterface;
    }
    //тут метод должен по названию категории получать список созданных к ней записей
    @Override
    public void executeCommand(Update update){
        String message = update.getMessage().getText();
        Long  chatId = update.getMessage().getChatId();
        List<Note> notes = noteInterface.getAllNotes();
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),INFO_NOTES);
        //проверка, что список не пустой
        if (!notes.isEmpty()) {
            for (Note note : notes) {
                //проверка, что текущий чат айди = чат айди
                //тут еще надо проверить, что введенное название категории соответствует категории этой записи в таблице
                // && message.equals(note.getCategoryName())
                if(chatId.equals(note.getChatId())){
                    sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), note.getNoteText());
                }
            }
        }else sendMessageInterface.sendMessage(update.getMessage().getChatId().toString()
                ,"There are no saved messages in this collection!");

    }
}
