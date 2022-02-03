package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DeleteCollectionCommand implements Command {

    SendMessageInterface sendMessageInterface;
    public DeleteCollectionCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }
    String DELETION_INF = "\uD83D\uDCAC You can have only 12 collection. So you can delete a collection any time you like. " +
            "Please notice: after you delete one of collections, all notes saved in this collection won't be available";

    @Override
    public void executeCommand(Update update) {
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),DELETION_INF);
    }
}
