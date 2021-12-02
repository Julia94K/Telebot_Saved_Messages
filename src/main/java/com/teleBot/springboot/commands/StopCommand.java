package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {
    public static final String STOP_MSG = "This bot was stopped!";
    private final SendMessageInterface sendMessageInterface;

    public StopCommand (SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),STOP_MSG);

    }
}
