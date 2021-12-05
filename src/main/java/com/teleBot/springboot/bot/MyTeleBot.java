package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.functions.CategoryFunction;
import com.teleBot.springboot.functions.SendMessageFunction;
import com.teleBot.springboot.functions.TgUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


import static com.teleBot.springboot.commands.NameOfCommand.NOT;

@Component
public class MyTeleBot extends TelegramLongPollingBot {


    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandList commandList;

    @Autowired
    public MyTeleBot(CategoryFunction addCategoryFunction, TgUserInterface tgUserInterface) {
        this.commandList = new CommandList(new SendMessageFunction(this),addCategoryFunction,tgUserInterface);

    }
//was
//    public MyTeleBot() {
//        this.commandList = new CommandList(new SendMessageFunction(this));
//
//    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            //positive case - we answer with one of existing commands (if exists)
            if (message.startsWith("/")) {
                String thisCommand = message.split(" ")[0].toLowerCase();
                commandList.processWrongMessages(thisCommand).executeCommand(update);
                System.out.println("command detected");
            } // negative case - we answer that this command is unknown
            else {
//                commandList.processWrongMessages(UnknownCommand.UNKNOWN_COMMAND).executeCommand(update);
                commandList.processWrongMessages(NOT.getNameOfCommand()).executeCommand(update);
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
