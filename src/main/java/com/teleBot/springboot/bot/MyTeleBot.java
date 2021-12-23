package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.Command;
import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.functions.*;
import com.teleBot.springboot.other.UserMessage;
import com.teleBot.springboot.repository.entity.Category;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
    //++
    private final TgUser tgUser;
    private final UserMessage userMessage;
    private final Category category;
    private final String PREFIX ="/";
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    SendMessageFunction sendMessageFunction;


    @Autowired
    public MyTeleBot(CategoryFunction addCategoryFunction, TgUserInterface tgUserInterface, NoteFunction noteFunction) {
        this.commandList = new CommandList(new SendMessageFunction(this),addCategoryFunction,tgUserInterface,noteFunction);
        //++
        this.tgUser = new TgUser();
        this.userMessage = new UserMessage(new SendMessageFunction(this),addCategoryFunction,noteFunction);
        this.category = new Category();


    }

    Command lastCommand;
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
            if(message.equals("/getcategories")||message.equals("Collections")){
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText(message);
                InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
                InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
                inlineKeyboardButton1.setText("Education");
                inlineKeyboardButton2.setText("Travel");
                inlineKeyboardButton3.setText("Work");
                inlineKeyboardButton4.setText("Shopping");
                inlineKeyboardButton5.setText("Restaurants");
                inlineKeyboardButton1.setCallbackData("Button \"Education\" has been pressed");
                inlineKeyboardButton2.setCallbackData("Button \"Travel\" has been pressed");
                inlineKeyboardButton3.setCallbackData("Button \"Work\" has been pressed");
                inlineKeyboardButton4.setCallbackData("Button \"Shopping\" has been pressed");
                inlineKeyboardButton5.setCallbackData("Button \"Restaurants\" has been pressed");
                List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                keyboardButtonsRow1.add(inlineKeyboardButton1);
                keyboardButtonsRow1.add(inlineKeyboardButton2);
                keyboardButtonsRow1.add(inlineKeyboardButton3);
                keyboardButtonsRow2.add(inlineKeyboardButton4);
                keyboardButtonsRow2.add(inlineKeyboardButton5);
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
            if(message.equals("/start")) {
                System.out.println(message);
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText(message);
//        sm.setText(getMessage(message));
                List<KeyboardRow> keyboard = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(true);
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
            if(message.equals("Help")) {
                System.out.println(message);
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                String helpMsg = "✨Дотупные команды✨" +
                        "\n" +
                        "/createcollection, /createnote, /getcategory, /getnotes";
                sm.setText(helpMsg);
                List<KeyboardRow> keyboard = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(true);
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


            //positive case - we answer with one of exids (if exists)sting comman
            //обработка команд

            if (message.startsWith(PREFIX)) {
                if(tgUser.isActive()){
                    tgUser.setActive(false);
                }
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
                //по идее не имеет смысла проверять, команда или нет. Бот должен уметь работать как с командами, так и с обычными сообщениями
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
