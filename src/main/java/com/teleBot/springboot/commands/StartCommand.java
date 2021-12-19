package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import com.teleBot.springboot.repository.entity.TgUser;
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
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), START_MSG);
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//        rowInline.add(InlineKeyboardButton.builder().text("Collections").callbackData("Collections_test").build());
//        rowsInline.add(rowInline);
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//

        //проверяем, есть ли пользователь в базе активных пользователей, если нет, то нужно сделать его активным
//        tgUserInterface.findUserByChatId(chatId).ifPresentOrElse(tgUser -> {
//            tgUser.setActive(true);
//            tgUserInterface.save(tgUser);
//        }, () -> {
//            TgUser tgUser = new TgUser();
//            tgUser.setActive(true);
//            tgUser.setChatId(chatId);
//            tgUserInterface.save(tgUser);
//
//        });

//

//
//
//
//
//
//
//
//        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//        KeyboardRow keyboardSecondRow = new KeyboardRow();
//
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(false);
//
//        keyboard.clear();
//        keyboardFirstRow.clear();
//        keyboardFirstRow.add("Collections");
//        keyboardFirstRow.add("Create Note");
//        keyboardSecondRow.add("Help");
//        keyboard.add(keyboardFirstRow);
//        keyboard.add(keyboardSecondRow);
//        replyKeyboardMarkup.setKeyboard(keyboard);

    }
}
