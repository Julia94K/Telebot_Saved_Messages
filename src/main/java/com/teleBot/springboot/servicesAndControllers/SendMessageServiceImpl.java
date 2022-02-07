package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.bot.MyTeleBot;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
@Controller
public class SendMessageServiceImpl implements SendMessageService {
    private final MyTeleBot myTeleBot;
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();


    public SendMessageServiceImpl(MyTeleBot myTeleBot) {
        this.myTeleBot = myTeleBot;
    }

    @Override
    public void sendMessage(String chatId, String message){
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(message);
//        sm.setReplyMarkup(inlineKeyboardMarkup);

        //обработка исключений
        try {
            myTeleBot.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}

