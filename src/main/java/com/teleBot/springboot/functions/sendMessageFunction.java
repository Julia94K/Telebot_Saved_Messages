package com.teleBot.springboot.functions;

import com.teleBot.springboot.bot.MyTeleBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class sendMessageFunction implements SendMessageInterface {
    private final MyTeleBot myTeleBot;

    public sendMessageFunction(MyTeleBot myTeleBot) {
        this.myTeleBot = myTeleBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(message);
        //тут нужно добавить обработку исключений
//        try {
//            myTeleBot.execute(sm);
//        }catch (TelegramApiException e){
//            e.printStackTrace();
//        }

//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
}
