package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SavePictureCommand implements Command{
    String ADD_Picture = "Choose and send new picture:";
    private final SendMessageService sendMessageService;

    public SavePictureCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;

    }

    @Override
    public void executeCommand(Update update){
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(),ADD_Picture);
    }
}
