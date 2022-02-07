package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.CategoryService;
import com.teleBot.springboot.servicesAndControllers.NoteService;
import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

//получение всех заметок вне зависимости от коллекций

public class GetNotesForCategoryCommand implements Command{

    private final SendMessageService sendMessageService;
    private final CategoryService categoryService;
    private final NoteService noteService;
    public final static String INFO_NOTES = "Notes in this collection:";


    public GetNotesForCategoryCommand(SendMessageService sendMessageService, CategoryService categoryService,
                                      NoteService noteService) {
        this.sendMessageService = sendMessageService;
        this.categoryService = categoryService;
        this.noteService = noteService;
    }

    @Override
    public void executeCommand(Update update){
        Long  chatId = update.getMessage().getChatId();
        List<Note> notes = noteService.getAllNotes();
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(),INFO_NOTES);
        //проверка, что список не пустой
        if (!notes.isEmpty()) {
            for (Note note : notes) {
                //проверка, что текущий чат айди = чат айди
                if(chatId.equals(note.getChatId())){
                    sendMessageService.sendMessage(update.getMessage().getChatId().toString(), note.getNoteText());
                }
            }
        }else sendMessageService.sendMessage(update.getMessage().getChatId().toString()
                ,"There are no saved messages in this collection!");

    }
}
