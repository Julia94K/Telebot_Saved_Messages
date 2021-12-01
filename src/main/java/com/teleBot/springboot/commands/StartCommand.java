package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{
    public static final String START_MSG = "Hello let's start!";
    private final SendMessageInterface sendMessageInterface;

    public StartCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),START_MSG);

    }
}
