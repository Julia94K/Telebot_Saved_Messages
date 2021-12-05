package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {
    public final static String STOP_MSG = "This bot was stopped!";
    private final SendMessageInterface sendMessageInterface;
    private final TgUserInterface tgUserInterface;

    public StopCommand (SendMessageInterface sendMessageInterface,TgUserInterface tgUserInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.tgUserInterface = tgUserInterface;
    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),STOP_MSG);
        tgUserInterface.findUserByChatId(update.getMessage().getChatId().toString())
                .ifPresent(it-> {
                    it.setActive(false);
                    tgUserInterface.save(it);
                });

    }
}
