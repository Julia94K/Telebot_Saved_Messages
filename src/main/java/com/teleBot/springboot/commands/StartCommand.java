package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {
    public static final String START_MSG = "Main menu:";
    private final SendMessageService sendMessageService;


    public StartCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void executeCommand(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MSG);

    }
}
