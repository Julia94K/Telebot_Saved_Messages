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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

import static com.teleBot.springboot.commands.NameOfCommand.*;

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
    private final String PREFIX ="/";
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Autowired
    public MyTeleBot(CategoryFunction addCategoryFunction, TgUserInterface tgUserInterface, NoteFunction noteFunction) {
        this.commandList = new CommandList(new SendMessageFunction(this),addCategoryFunction,tgUserInterface,noteFunction);
        //++
        this.tgUser = new TgUser();
        this.userMessage = new UserMessage(new SendMessageFunction(this),addCategoryFunction,noteFunction);

    }

    Command lastCommand;
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            //positive case - we answer with one of exids (if exists)sting comman
            //обработка команд

            if (message.startsWith(PREFIX)) {
                String thisCommand = message.split(" ")[0].toLowerCase();
                commandList.processWrongMessages(thisCommand).executeCommand(update);
                System.out.println("command detected");
                if(update.getMessage().getText().equals("/savecategory")){
                    tgUser.setActive(true);
                    tgUser.setUserStatus(0);
                }
                if(update.getMessage().getText().equals("/savenote")){
                    tgUser.setActive(true);
                    tgUser.setUserStatus(1);
                }
            }
            //обработка простых сообщений от пользователя (когда ожидается ввод)
            else if (tgUser.isActive()&&tgUser.getUserStatus().equals(0)){
                userMessage.proceedSimpleMessage(update);
                tgUser.setActive(false);
            }
            else if(tgUser.isActive()&&tgUser.getUserStatus().equals(1)){
                userMessage.proceedNote(update);
                tgUser.setUserStatus(2);
//                tgUser.setActive(false);
            }
            else if(tgUser.isActive()&&tgUser.getUserStatus().equals(2)){
                userMessage.saveNoteText(update);
                tgUser.setActive(false);
            }
            else {
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

    //клавиатура
    public String getMessage (String msg){
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        if(msg.equals("/start")||msg.equals("help")){
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Collections");
            keyboardFirstRow.add("Create Note");
            keyboardSecondRow.add("Help");
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Выбрать";
        }


        return "Press /help in case of Problems";

    }
}
