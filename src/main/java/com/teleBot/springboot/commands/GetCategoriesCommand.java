package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class GetCategoriesCommand implements Command {
    private final SendMessageInterface sendMessageInterface;

    public GetCategoriesCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;

    }
    @Override
    public void executeCommand (Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),
                "If you want to delete one of the collections, press /deletecollection");
    }
}
