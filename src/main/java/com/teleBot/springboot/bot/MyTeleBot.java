package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.Command;
import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.commands.HelpCommand;
import com.teleBot.springboot.commands.SaveNoteCommand;
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
import java.util.HashMap;
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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            //заменить обычные слова на команды
            switch (message) {
                case "Help":
                    message = "/help";
                    break;
                case "Home":
                    message = "/start";
                    break;
                case "Collections":
                    message = "/getcategories";
                    break;
                case "New collection":
                    message = "/savecategory";
                    break;
                case "New note":
                    message = "/savenote";
                    break;
            }

            if (message.equals("/getcategories")) {
                // || message.equals("Collections")
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText("\uD83E\uDDA9 Chose one of the collections or select a command bellow: \uD83E\uDDA9");

                List<Category> categories = categoryInterface.getAllCategories();
                List<InlineKeyboardButton> keyboardButtonsRow_1 = new ArrayList<>();
                InlineKeyboardButton inlineKeyboardButton_1 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton_2 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton_3 = new InlineKeyboardButton();
                keyboardButtonsRow_1.add(inlineKeyboardButton_1);
                keyboardButtonsRow_1.add(inlineKeyboardButton_2);
                keyboardButtonsRow_1.add(inlineKeyboardButton_3);

                List<InlineKeyboardButton> keyboardButtonsRow_2 = new ArrayList<>();
                List<List<InlineKeyboardButton>> rowList_new = new ArrayList<>();
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();

                //значение кнопки постоянно перезаписывается, а нужно, чтобы в лист все время добавлялось новое

                if (!categories.isEmpty()) {
                    for (Category category : categories) {
//                        inlineKeyboardButton.setText(category.getCategoryName());
//                        inlineKeyboardButton.setCallbackData(category.getCategoryName());
                        //-----OK-----//
//                        System.out.println(inlineKeyboardButton);
//                        buttons.add(inlineKeyboardButton);
//                        rowList_new.add(buttons);
//                        inlineKeyboardMarkup.setKeyboard(rowList_new);
//                        sm.setReplyMarkup(inlineKeyboardMarkup);
                    }
                }
//                }
//                try {
//                    execute(sm);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
                //hardcoded values for buttons
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
            if (message.equals("/start") || message.equals("Home \uD83C\uDFE0")) {
                System.out.println(message);
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText("Choose one the options bellow");
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                KeyboardRow row2 = new KeyboardRow();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);
                System.out.println(message);
                row.add("Help"); // Help
                row.add("Collections"); // Collections
                row2.add("New collection"); // Save note
                row2.add("New note"); // Save collection
                keyboard.add(row);
                keyboard.add(row2);
                replyKeyboardMarkup.setKeyboard(keyboard);
                sm.setReplyMarkup(replyKeyboardMarkup);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (message.equals("Create note") || message.equals("/savenote")) {
                Long chat_id = update.getMessage().getChatId();
                List<Category> categories = categoryInterface.getAllCategories();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row1 = new KeyboardRow();
                KeyboardRow row2 = new KeyboardRow();
                KeyboardRow row3 = new KeyboardRow();
                KeyboardRow row4 = new KeyboardRow();

                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);

                //тут код, чтобы отобразить категории из базы в виде кнопок меню
                if (!categories.isEmpty()) {
                    for (Category category : categories) {
                        if (chat_id.equals(category.getChatId())) {
                            if (row1.size() <= 3) {
                                row1.add(category.getCategoryName());
                            } else if (row2.size() <= 3) {
                                row2.add(category.getCategoryName());
                            } else {
                                row3.add(category.getCategoryName());
                            }
                        }
                    }
                    row4.add("Home");
                    keyboard.add(row1);
                    keyboard.add(row2);
                    keyboard.add(row3);
                    keyboard.add(row4);
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

            //обработка команд
            if (message.startsWith(PREFIX)) {
                if (tgUser.isActive()) {
                    tgUser.setActive(false);
                }
                String thisCommand = message.split(" ")[0].toLowerCase();
                commandList.processWrongMessages(thisCommand).executeCommand(update);
                System.out.println("command detected");
                if (update.getMessage().getText().equals("/savecategory")||(update.getMessage().getText().equals("New collection"))) {
                    tgUser.setActive(true);
                    tgUser.setUserStatus(0);
                }
                if (update.getMessage().getText().equals("/savenote") || update.getMessage().getText().equals("New note")) {
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
            } else if (tgUser.isActive() && tgUser.getUserStatus().equals(2)) {
                userMessage.saveNoteText(update);
                tgUser.setActive(false);
            } else {
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
    public void getNotesForCategory(String callbackData, String chatId) {
        SendMessage sm = new SendMessage();
        List<Note> notes = noteInterface.getAllNotes();
        List<String> values = new ArrayList<>();
        for (Note n : notes) {
            values.add(n.getCategoryName());
            System.out.println(n);
        }
        System.out.println(values);
        if (values.contains(callbackData) && !notes.isEmpty()) {
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
            sm.setText("The collection "+callbackData.toUpperCase()+" is empty");
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
