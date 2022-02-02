package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SavePictureCommand implements Command{
    String ADD_Picture = "Choose and send new picture:";
    private final SendMessageInterface sendMessageInterface;

    public SavePictureCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),ADD_Picture);
    }
}
