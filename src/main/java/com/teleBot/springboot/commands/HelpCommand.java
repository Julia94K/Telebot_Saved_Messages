package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command{
    public static final String HELP_MSG = "Following commands are supported: /start, /stop, /help, /addcategory";
    private final SendMessageInterface sendMessageInterface;

    public HelpCommand (SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),HELP_MSG);

    }
}
