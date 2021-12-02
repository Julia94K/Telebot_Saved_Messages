package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AddNewCategory implements Command {
    public static final String ADD_MSG = "You can add new category!";
    private final SendMessageInterface sendMessageInterface;

    public AddNewCategory (SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),ADD_MSG);
    }
}
