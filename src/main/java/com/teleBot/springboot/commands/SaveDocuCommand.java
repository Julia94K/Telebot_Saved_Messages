package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SaveDocuCommand implements Command{
    String ADD_Docu = "Choose and send new document:";
    private final SendMessageService sendMessageService;

    public SaveDocuCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }


    @Override
    public void executeCommand(Update update){
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(),ADD_Docu);
    }
}
