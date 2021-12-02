package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.commands.NotACommand;
import com.teleBot.springboot.commands.StartCommand;
import com.teleBot.springboot.commands.UnknownCommand;
import com.teleBot.springboot.functions.SendMessageFunction;
import com.teleBot.springboot.functions.SendMessageInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTeleBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandList commandList;

    public MyTeleBot() {
        this.commandList = new CommandList(new SendMessageFunction(this));

    }


    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("!!!");
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            //positive case - we answer with one of existing commands (if exists)
            if (message.startsWith("/")) {
                String thisCommand = message.split(" ")[0].toLowerCase();
                commandList.processWrongMessages(thisCommand).executeCommand(update);
                System.out.println("command detected");
            } // negative case - we answer that this command is unknown
            else {
                commandList.processWrongMessages(UnknownCommand.UNKNOWN_COMMAND).executeCommand(update);
            }
        }
    }


    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
