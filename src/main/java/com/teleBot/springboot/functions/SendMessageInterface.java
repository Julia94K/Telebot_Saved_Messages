package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.TgUser;

import java.util.function.Consumer;

public interface SendMessageInterface {
    //send messages from the bot with parameters message and chat id

    Consumer<? super TgUser> sendMessage (String chatId, String message);


}
