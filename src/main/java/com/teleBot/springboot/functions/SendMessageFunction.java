package com.teleBot.springboot.functions;

import com.teleBot.springboot.bot.MyTeleBot;
import com.teleBot.springboot.repository.entity.TgUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.function.Consumer;

public class SendMessageFunction implements SendMessageInterface {
    private final MyTeleBot myTeleBot;

    public SendMessageFunction(MyTeleBot myTeleBot) {
        this.myTeleBot = myTeleBot;
    }
//    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();


    @Override
    public Consumer<? super TgUser> sendMessage(String chatId, String message) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(message);
//        sm.setReplyMarkup(replyKeyboardMarkup);
        //обработка исключений
        try {
            myTeleBot.execute(sm);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
        return null;
    }

    //новый класс для клавиатуры
//    String getMessage(String message){
//        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//        KeyboardRow keyboardSecondRow = new KeyboardRow();
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(false);

//        if(message.equals("/start")){
//            keyboard.clear();
//            keyboardFirstRow.clear();
//            keyboardFirstRow.add("Categories");
//            keyboardFirstRow.add("Add new note");
//            keyboardSecondRow.add("Help");
//            keyboard.add(keyboardFirstRow);
//            keyboard.add(keyboardSecondRow);
//            replyKeyboardMarkup.setKeyboard(keyboard);
//            return "Select";
//        }
//        return message;

}
