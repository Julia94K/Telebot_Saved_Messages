package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DeleteCollectionCommand implements Command {

    SendMessageService sendMessageService;
    public DeleteCollectionCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }
    String DELETION_INF = "\uD83D\uDCAC You can have only 12 collection. So you can delete a collection any time you like. " +
            "Please notice: after you delete one of the collections, all notes saved in this collection will be deleted as well";

    @Override
    public void executeCommand(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(),DELETION_INF);
    }
}
