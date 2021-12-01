package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command{
    public static final String UNKNOWN_COMMAND = "This command does't exist";
    private final SendMessageInterface sendMessageInterface;

    public UnknownCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),UNKNOWN_COMMAND);
    }
}
