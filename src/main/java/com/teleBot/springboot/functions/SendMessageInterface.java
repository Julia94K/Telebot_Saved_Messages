package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.TgUser;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.function.Consumer;

public interface SendMessageInterface {
    //send messages from the bot with parameters message and chat id
    //chatId ? long string

    void sendMessage (String  chatId, String message);


}
