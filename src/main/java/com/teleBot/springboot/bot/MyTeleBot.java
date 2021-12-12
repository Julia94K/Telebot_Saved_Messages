package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.Command;
import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.functions.*;
import com.teleBot.springboot.repository.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


import java.util.ArrayList;

import static com.teleBot.springboot.commands.NameOfCommand.NOT;
import static com.teleBot.springboot.commands.NameOfCommand.START;

@Component
public class MyTeleBot extends TelegramLongPollingBot {


    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandList commandList;

    @Autowired
    public MyTeleBot(CategoryFunction addCategoryFunction, TgUserInterface tgUserInterface, NoteFunction noteFunction) {
        this.commandList = new CommandList(new SendMessageFunction(this),addCategoryFunction,tgUserInterface,noteFunction);

    }

    String lastCommand;

    //класс
//    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    //ввести параметр lastMessage, чтобы обрабатывать последние введенные сообщения


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            //added for keyboard




            //positive case - we answer with one of existing commands (if exists)
            if (message.startsWith("/")) {
                String thisCommand = message.split(" ")[0].toLowerCase();
                commandList.processWrongMessages(thisCommand).executeCommand(update);
                System.out.println("command detected");}
//            } else if (
//            update.getMessage().getText(). -> category contains message
                //то выдать все сообщения по этой категории
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

    public String getMessage (String msg){


        return "Press /help in case of Problems";

    }
}
