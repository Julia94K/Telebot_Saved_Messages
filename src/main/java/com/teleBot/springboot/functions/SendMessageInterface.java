package com.teleBot.springboot.functions;

public interface SendMessageInterface {
    //send messages from the bot with parameters message and chat id

    void sendMessage (String chatId, String message);


}
