package com.teleBot.springboot.servicesAndControllers;

public interface SendMessageService {
    //send messages from the bot with parameters message and chat id
    //chatId ? long string
    void sendMessage (String  chatId, String message);
}
