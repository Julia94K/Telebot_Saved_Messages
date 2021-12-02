package com.teleBot.springboot.functions;

import com.teleBot.springboot.bot.MyTeleBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendMessageFunction implements SendMessageInterface {
    private final MyTeleBot myTeleBot;

    public SendMessageFunction(MyTeleBot myTeleBot) {
        this.myTeleBot = myTeleBot;
    }


    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(message);
        //обработка исключений
        try {
            myTeleBot.execute(sm);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
