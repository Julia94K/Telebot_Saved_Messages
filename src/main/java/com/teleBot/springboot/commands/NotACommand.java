package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NotACommand implements Command {
    public static final String NOT_COMMAND = "Sorry, I can only work with commands not words!";
    private final SendMessageInterface sendMessageInterface;

    public NotACommand (SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),NOT_COMMAND);

    }
}
