package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import com.teleBot.springboot.repository.entity.TgUser;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {
    public static final String START_MSG = "Hello let's start!";
    private final SendMessageInterface sendMessageInterface;
    private final TgUserInterface tgUserInterface;

    public StartCommand(SendMessageInterface sendMessageInterface, TgUserInterface tgUserInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.tgUserInterface = tgUserInterface;
    }

    @Override
    public void executeCommand(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        //проверяем, есть ли пользователь в базе активных пользователей, если нет, то нужно сделать его активным
        tgUserInterface.findUserByChatId(chatId).ifPresentOrElse(tgUser -> {
            tgUser.setActive(true);
            tgUserInterface.save(tgUser);
        }, () -> {
            TgUser tgUser = new TgUser();
            tgUser.setActive(true);
            tgUser.setChatId(chatId);
            tgUserInterface.save(tgUser);

        });

        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), START_MSG);

    }
}
