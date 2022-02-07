package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
//probably not needed. Could be replaced with some default keyboard with options
public class UnknownCommand implements Command{
    public static final String UNKNOWN_COMMAND = "This command does't exist";
    private final SendMessageService sendMessageService;

    public UnknownCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(),UNKNOWN_COMMAND);
    }
}
