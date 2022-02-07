package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;


public class SaveNoteCommand implements Command {

    String ADD_NOTE = "Select a category:";
    private final SendMessageService sendMessageService;


    public SaveNoteCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }


    @Override
    public void executeCommand(Update update){
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), ADD_NOTE);

        //обработка команлды происходит в классе UserMessage
    }



}
