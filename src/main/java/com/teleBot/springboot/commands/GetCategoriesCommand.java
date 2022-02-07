package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GetCategoriesCommand implements Command {
    private final SendMessageService sendMessageService;

    public GetCategoriesCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;

    }
    @Override
    public void executeCommand (Update update){
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(),
                "If you want to delete one of the collections, press /deletecollection");
    }
}
