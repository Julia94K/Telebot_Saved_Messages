package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SaveDocuCommand implements Command{
    String ADD_Docu = "Choose and send new document:";
    private final SendMessageInterface sendMessageInterface;

    public SaveDocuCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }


    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),ADD_Docu);
    }
}
