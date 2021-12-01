package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.functions.SendMessageInterface;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyTeleBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandList commandList;

    //тут какая-то ерунда исправить

    public MyTeleBot() {

        this.commandList = new CommandList(new SendMessageInterface() {
            @Override
            public void sendMessage(String chatId, String message) {

            }
        });
    }


    @Override
    public void onUpdateReceived(Update update){
        System.out.println("!!!");
        if(update.hasMessage()&&update.getMessage().hasText()){
            String message = update.getMessage().getText();
            if(message.startsWith("/")){
                commandList.
            }
            else {
                commandList.processWrongMessages().executeCommand(update);
            }
        }
    }

    @Override
    public String getBotUsername(){
        return username;
    }

    @Override
    public String getBotToken(){
        return token;
    }
}
