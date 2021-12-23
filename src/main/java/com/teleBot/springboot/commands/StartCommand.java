package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import com.teleBot.springboot.repository.entity.TgUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class StartCommand implements Command {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
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
//        long chat_id = update.getMessage().getChatId();
//        SendMessage message1 = new SendMessage().setChatId(chat_id).setText("Keyboard");
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        List<KeyboardRow>keyboard = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//        row.add("Row 1 Button 1");
//        row.add("Row 1 Button 2");
//        row.add("Row 1 Button 3");
//        keyboard.add(row);
//        row = new KeyboardRow();
//        row.add("Row 2 Button 1");
//        row.add("Row 2 Button 2");
//        row.add("Row 2 Button 3");
//        keyboard.add(row);
//        keyboardMarkup.setKeyboard(keyboard);
//
//        message1.setReplyMarkup(keyboardMarkup);
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), START_MSG);



    }
}
