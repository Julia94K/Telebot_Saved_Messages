package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.Command;
import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.functions.*;
import com.teleBot.springboot.other.UserMessage;
import com.teleBot.springboot.repository.entity.Category;
import com.teleBot.springboot.repository.entity.Note;
import com.teleBot.springboot.repository.entity.TgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.teleBot.springboot.commands.NameOfCommand.*;

@Component
public class MyTeleBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandList commandList;
    private final TgUser tgUser;
    private final UserMessage userMessage;
    private final Category category;
    private final Note note;
    private final String PREFIX = "/";
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    SendMessageFunction sendMessageFunction;
    private final CategoryInterface categoryInterface;
    private final NoteInterface noteInterface;


    @Autowired
    public MyTeleBot(CategoryFunction addCategoryFunction, TgUserInterface tgUserInterface, NoteFunction noteFunction,
                     CategoryInterface categoryInterface, NoteInterface noteInterface) {
        this.commandList = new CommandList(new SendMessageFunction(this), addCategoryFunction, tgUserInterface, noteFunction);
        //++
        this.tgUser = new TgUser();
        this.userMessage = new UserMessage(new SendMessageFunction(this), addCategoryFunction, noteFunction);
        this.category = new Category();
        this.note = new Note();
        this.categoryInterface = categoryInterface;
        this.noteInterface = noteInterface;


    }

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();

    ReplyKeyboardMarkup replyKeyboardMarkup1 = new ReplyKeyboardMarkup();
    List<KeyboardRow> keyboard1 = new ArrayList<>();
    KeyboardRow row = new KeyboardRow();

    @Override
    public void onUpdateReceived(Update update) {
        //TODO оптимизировать кнопки, добавить CallbackQuery

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.equals("/getcategories") || message.equals("Collections")) {
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText("\uD83E\uDDA9 Chose one of the collections or select a command bellow: \uD83E\uDDA9");

                //++
                //попытка вывести коллекции в виде кнопок TODO переделать цикл

                //варианты: создать массив объектов-кнопок
                //заполнить этот массив кнопками
//                InlineKeyboardButton [] inlineKeyboardButtons = new InlineKeyboardButton[10];
//
//                for (InlineKeyboardButton ib:inlineKeyboardButtons){
//                    ib.setText(category.getCategoryName());
//                    ib.setCallbackData(category.getCategoryName());
//                    System.out.println(ib);
//                }


//                InlineKeyboardButton inlineKeyboardButtonB = new InlineKeyboardButton();
//                List<Category> categories = categoryInterface.getAllCategories();
//                List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
//                List<List<InlineKeyboardButton>> rowList1 = new ArrayList<>();
//                if(!categories.isEmpty()){
//                    for (Category category:categories){
//                        inlineKeyboardButtonB.setText(category.getCategoryName());
//                        inlineKeyboardButtonB.setCallbackData(category.getCategoryName());
//                        System.out.println(category.getCategoryName());
//                    }
//                    keyboardButtonsRow.add(inlineKeyboardButtonB);
//                    rowList1.add(keyboardButtonsRow);
//                    inlineKeyboardMarkup.setKeyboard(rowList1);
//                    System.out.println(rowList1);
//                    sm.setReplyMarkup(inlineKeyboardMarkup);
////                    try{
////                        execute(sm);
////                    } catch (TelegramApiException e){
////                        e.printStackTrace();
////                    }
//
//
//                }

                //commented replace

                InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton6 = new InlineKeyboardButton();
                inlineKeyboardButton1.setText("Education");
                inlineKeyboardButton2.setText("Travel");
                inlineKeyboardButton3.setText("Work");
                inlineKeyboardButton4.setText("Shopping");
                inlineKeyboardButton5.setText("Restaurants");
                inlineKeyboardButton6.setText("Movies");
                inlineKeyboardButton1.setCallbackData("Education");
                inlineKeyboardButton2.setCallbackData("Travel");
                inlineKeyboardButton3.setCallbackData("Work");
                inlineKeyboardButton4.setCallbackData("Shopping");
                inlineKeyboardButton5.setCallbackData("Restaurants");
                inlineKeyboardButton6.setCallbackData("Movies");
                List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                keyboardButtonsRow1.add(inlineKeyboardButton1);
                keyboardButtonsRow1.add(inlineKeyboardButton2);
                keyboardButtonsRow1.add(inlineKeyboardButton3);
                keyboardButtonsRow2.add(inlineKeyboardButton4);
                keyboardButtonsRow2.add(inlineKeyboardButton5);
                keyboardButtonsRow2.add(inlineKeyboardButton6);
                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(keyboardButtonsRow1);
                rowList.add(keyboardButtonsRow2);
                inlineKeyboardMarkup.setKeyboard(rowList);
                sm.setReplyMarkup(inlineKeyboardMarkup);


                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
            if (message.equals("/start")) {
                System.out.println(message);
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText("Choose one the options bellow");
//        sm.setText(getMessage(message));
                List<KeyboardRow> keyboard = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);
                System.out.println(message);
                row.add("Help");
                row.add("Collections");
                row.add("Create note");
                keyboard.add(row);
                replyKeyboardMarkup.setKeyboard(keyboard);
                sm.setReplyMarkup(replyKeyboardMarkup);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if(message.equals("Create note")){

                List <Category> categories = categoryInterface.getAllCategories();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row1 = new KeyboardRow();
                KeyboardRow row2 = new KeyboardRow();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);

                //тут код, чтобы отобразить категории из базы в виде кнопок меню
                //TODO как сделать, чтобы кнопки выгляделим нормально и запускалась логика на сохранение заметок
                if(!categories.isEmpty()){
                    for (Category category :categories){
                        if(row1.size()<=3){
                            row1.add(category.getCategoryName());
                        }
                        else {
                            row2.add(category.getCategoryName());
                        }

                    }
                    keyboard.add(row);
                    keyboard.add(row2);
                    replyKeyboardMarkup.setKeyboard(keyboard);
                    SendMessage sm = new SendMessage();
                    sm.setChatId(update.getMessage().getChatId().toString());
                    sm.setText("Chose one of the collections");
                    sm.setReplyMarkup(replyKeyboardMarkup);
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (message.equals("Help")) {
                System.out.println(message);
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText(message);
                List<KeyboardRow> keyboard = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);
                row.add("Collections");
                row.add("Create note");
                keyboard.add(row);
                replyKeyboardMarkup.setKeyboard(keyboard);
                sm.setReplyMarkup(replyKeyboardMarkup);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            //обработка команд

            if (message.startsWith(PREFIX)) {
                if (tgUser.isActive()) {
                    tgUser.setActive(false);
                }
                String thisCommand = message.split(" ")[0].toLowerCase();
                commandList.processWrongMessages(thisCommand).executeCommand(update);
                System.out.println("command detected");
                if (update.getMessage().getText().equals("/savecategory")) {

                    tgUser.setActive(true);
                    tgUser.setUserStatus(0);

                }
                if (update.getMessage().getText().equals("/savenote")||update.getMessage().getText().equals("Create note")) {
                    tgUser.setActive(true);
                    tgUser.setUserStatus(1);
                }
            }


            //обработка простых сообщений от пользователя (когда ожидается ввод)
            else if (tgUser.isActive() && tgUser.getUserStatus().equals(0)) {
                userMessage.proceedSimpleMessage(update);
                tgUser.setActive(false);
            } else if (tgUser.isActive() && tgUser.getUserStatus().equals(1)) {
                userMessage.proceedNote(update);
                tgUser.setUserStatus(2);
//                tgUser.setActive(false);
            } else if (tgUser.isActive() && tgUser.getUserStatus().equals(2)) {
                userMessage.saveNoteText(update);
                tgUser.setActive(false);
            } else {
                //по идее не имеет смысла проверять, команда или нет. Бот должен уметь работать как с командами, так и с обычными сообщениями
//                commandList.processWrongMessages(NOT.getNameOfCommand()).executeCommand(update);
                //default keyboard + help with commands
                //TODO method for default keyboard
                commandList.processWrongMessages(HELP.getNameOfCommand()).executeCommand(update);


            }
        }
        //if user pressed one of the buttons
        else if (update.hasCallbackQuery()) {
            //метод, в который передается значение callData и в зависимости от этого значения реализуется функция
            String callData = update.getCallbackQuery().getData();
            String chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
            getNotesForCategory(callData, chat_id);

        }


    }


    //TODO класс для вызова дефолтной клавиатуры


    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    //универсальный метод, который получает на вход значение коллекции и чат айди и ищет все заметки,
    // сохраненные для данной коллеции
    //TODO как сделать так, чтобы в случае, если в данном методе ничего не было найдено, то возвращался текст "коллекция пустая"
    public void   getNotesForCategory(String callbackData, String chatId) {
        SendMessage sm = new SendMessage();
        List <Note> notes = noteInterface.getAllNotes();
        List <String> values = new ArrayList<>();
        for (Note n: notes){
            values.add(n.getCategoryName());
            values.add(n.getChatId().toString());
        }
        //TODO только для этого чат айди
        if(values.contains(callbackData)&&!notes.isEmpty()){
//            if (!notes.isEmpty()) {
                //condition: only this collection name and only for this user (based on chatId)
                for (Note note : notes) {
                    if (callbackData.equals(note.getCategoryName()) && chatId.equals(note.getChatId().toString())) {
                        sm.setChatId(chatId);
                        sm.setText(note.getNoteText());

                        System.out.println(note.getNoteText());
                        try {
                            execute(sm);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        //if the list with notes is empty for this user
        else {
            sm.setChatId(chatId);
            sm.setText("Nothing was found");
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
