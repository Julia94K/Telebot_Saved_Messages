package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatisticCommand implements Command{

    private final TgUserInterface tgUserInterface;
    private final SendMessageInterface sendMessageInterface;
    private String STAT_MSG = "Active users: %s ";

    public StatisticCommand(TgUserInterface tgUserInterface, SendMessageInterface sendMessageInterface) {
        this.tgUserInterface = tgUserInterface;
        this.sendMessageInterface = sendMessageInterface;
    }

    @Autowired
    public StatisticCommand(SendMessageInterface sendMessageInterface, TgUserInterface tgUserInterface){
        this.sendMessageInterface = sendMessageInterface;
        this.tgUserInterface = tgUserInterface;
    }

    @Override
    public void executeCommand(Update update){
        int actUser = tgUserInterface.getAllActiveUsers().size();
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),String.format(STAT_MSG,actUser));

    }

}
