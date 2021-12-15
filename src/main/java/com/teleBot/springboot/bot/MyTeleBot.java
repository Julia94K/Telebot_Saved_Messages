package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.Command;
import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.functions.*;
import com.teleBot.springboot.other.UserMessage;
import com.teleBot.springboot.repository.entity.TgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


import static com.teleBot.springboot.commands.NameOfCommand.NOT;

@Component
public class MyTeleBot extends TelegramLongPollingBot {


    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandList commandList;
    //++
    private final TgUser tgUser;
    private final UserMessage userMessage;

    @Autowired
    public MyTeleBot(CategoryFunction addCategoryFunction, TgUserInterface tgUserInterface, NoteFunction noteFunction) {
        this.commandList = new CommandList(new SendMessageFunction(this),addCategoryFunction,tgUserInterface,noteFunction);
        //++
        this.tgUser = new TgUser();
        this.userMessage = new UserMessage(new SendMessageFunction(this),addCategoryFunction);

    }

    Command lastCommand;
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    //класс
//    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    //ввести параметр lastMessage, чтобы обрабатывать последние введенные сообщения


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            //++
            Message inMessage = update.getMessage();
            SendMessage outMessage = new SendMessage();
            //++


            String message = update.getMessage().getText().trim();





            //positive case - we answer with one of existing commands (if exists)
            //обработка команд
            if (message.startsWith("/")) {
                String thisCommand = message.split(" ")[0].toLowerCase();
                commandList.processWrongMessages(thisCommand).executeCommand(update);
                tgUser.setActive(true);
                System.out.println("command detected");}

            //++

            //обработка простых сообщений от пользователя (когда ожидается ввод)
//
            else if (tgUser.isActive()){
                //TODO выполнить действие - дописать
                userMessage.proceedSimpleMessage(update);
                tgUser.setActive(false);


            }
                //то выдать все сообщения по этой категории
            else {
                commandList.processWrongMessages(NOT.getNameOfCommand()).executeCommand(update);
            }
        }
        //написать метод, который будет считывать коллбэк пользователя и на основании этого коллбэка делать действие
//        else if (update.hasCallbackQuery()){
//            processCallbackQuery(update);
//        }
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
