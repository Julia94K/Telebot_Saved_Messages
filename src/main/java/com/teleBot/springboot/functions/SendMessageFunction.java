package com.teleBot.springboot.functions;

import com.teleBot.springboot.bot.MyTeleBot;
import com.teleBot.springboot.repository.entity.TgUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SendMessageFunction implements SendMessageInterface {
    private final MyTeleBot myTeleBot;

    public SendMessageFunction(MyTeleBot myTeleBot) {
        this.myTeleBot = myTeleBot;
    }

    @Override
    public void sendMessage(String chatId, String message){
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(message);

        //обработка исключений
        try {
            myTeleBot.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}

