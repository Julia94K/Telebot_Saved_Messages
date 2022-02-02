package com.teleBot.springboot.bot;

import com.teleBot.springboot.commands.CommandList;
import com.teleBot.springboot.functions.*;
import com.teleBot.springboot.other.UserMessage;
import com.teleBot.springboot.repository.entity.Category;
import com.teleBot.springboot.repository.entity.Document;
import com.teleBot.springboot.repository.entity.Note;
import com.teleBot.springboot.repository.entity.Pictures;
import com.teleBot.springboot.repository.entity.TgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
    private final CategoryInterface categoryInterface;
    private final NoteInterface noteInterface;
    private final PictureInterface pictureInterface;
    private final DocumentInterface documentInterface;


    @Autowired
    public MyTeleBot(CategoryFunction addCategoryFunction, TgUserInterface tgUserInterface, NoteFunction noteFunction,
                     CategoryInterface categoryInterface, NoteInterface noteInterface, PictureInterface pictureInterface,
                     DocumentInterface documentInterface) {
        this.commandList = new CommandList(new SendMessageFunction(this), tgUserInterface, addCategoryFunction, noteFunction);
        //++
        this.tgUser = new TgUser();
        this.userMessage = new UserMessage(new SendMessageFunction(this), addCategoryFunction, noteFunction);
        this.category = new Category();
        this.note = new Note();
        this.categoryInterface = categoryInterface;
        this.noteInterface = noteInterface;
        this.pictureInterface = pictureInterface;
        this.documentInterface = documentInterface;
    }

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
    List<List<InlineKeyboardButton>> rowList= new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = handleString(update);
            if (message.equals("/getcategories")) {
                SendMessage sm = getSendMessageCategories(update);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if(message.equals("Delete collection")){
                SendMessage sm = prepareCategoriesToDelete(update);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if(message.equals("Pictures \uD83D\uDDBC")){
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText("Choose one the options bellow");
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);
                row.add("New picture");
                row.add("Saved pictures");
                row.add("Home");
                keyboard.add(row);
                replyKeyboardMarkup.setKeyboard(keyboard);
                sm.setReplyMarkup(replyKeyboardMarkup);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if(message.equals("Documents \uD83D\uDCDA")){
                SendMessage sm = new SendMessage();
                sm.setChatId(update.getMessage().getChatId().toString());
                sm.setText("Choose one the options bellow:");
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(false);
                row.add("New document");
                row.add("Saved documents");
                row.add("Home");
                keyboard.add(row);
                replyKeyboardMarkup.setKeyboard(keyboard);
                sm.setReplyMarkup(replyKeyboardMarkup);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if(message.equals("Saved documents")){
                SendDocument sendDocument = new SendDocument();
                showDocuments(update, sendDocument);
            }
            if(message.equals("Saved pictures")){
                System.out.println("test");
                SendPhoto sendPhoto = new SendPhoto();
                showPictures(update, sendPhoto);
            }
            if (message.equals("/start")) {
                SendMessage sm = getSendMessageStart(update);
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (message.equals("New note") || message.equals("/savenote")) {
                sendMessageCreate(update);
            }
            updateUserStatus(update, message);
        }
        //метод для загрузки документа в БД
        else if(update.hasMessage()&&update.getMessage().hasDocument()){
            saveDocument(update);
        }
        //метод для загрузки фото/картинок в БД
        else if(update.hasMessage()&&update.getMessage().hasPhoto()){
            savePicture(update);
        }
        //if user pressed one of the buttons
        else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            String chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
            //удаление категории из списка
            List<String> toDeleteNotes = new ArrayList<>();
            List<String> toDeleteDocuments = new ArrayList<>();
            List<String> toDeletePictures = new ArrayList<>();
            List<Note> notes = noteInterface.getAllNotes();
            for (Note note : notes) {
                toDeleteNotes.add(note.getUpdateId().toString());
            }

            List<Document> documents = documentInterface.getAllItems();
            for (Document document:documents){
                toDeleteDocuments.add(document.getUpdateId().toString());
            }
            List<Pictures> pictures = pictureInterface.getAllItems();
            for (Pictures picture:pictures){
                toDeletePictures.add(picture.getUpdateId().toString());
            }
            //метод для удаления заметки
            if (toDeleteNotes.contains(callData)) {
                deleteNote(callData, chat_id);
            } // метод для удаления документа
            else if(toDeleteDocuments.contains(callData)){
                deleteDocument(callData,chat_id);
            } // метод для удаления картинки
            else if(toDeletePictures.contains(callData)){
                deletePicture(callData,chat_id);
            }//метод для удаления коллекций пользователя
            else if(callData.contains("❌")){
                deleteCollection(callData,chat_id);
            }
            else {
                //метод, в который передается значение callData и в зависимости от этого значения отображается необходимая коллекция
                getNotesForCategory(callData, chat_id);
            }
        }
    }

    private void savePicture(Update update) {
        List<PhotoSize> photos = update.getMessage().getPhoto();
        String file_id = Objects.requireNonNull(update.getMessage().getPhoto().stream().max(Comparator.comparing(PhotoSize::getFileSize))
                .orElse(null)).getFileId();
//        String fileId = String.valueOf(update.getMessage().getPhoto().stream().max(Comparator.comparing(PhotoSize::getFileId)));
        Pictures picture = new Pictures();
        picture.setChatId(update.getMessage().getChatId());
        picture.setUpdateId(update.getUpdateId());
        picture.setCategoryName("Pictures");
        picture.setPictureFileId(file_id);
        pictureInterface.save(picture);
        String message2 = "Saved!";
        SendMessage sm = new SendMessage();
        sm.setText(message2);
        sm.setChatId(update.getMessage().getChatId().toString());
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void showPictures(Update update, SendPhoto sendPhoto) {
        Long chat_id = update.getMessage().getChatId();
        List<Pictures> pictures = pictureInterface.getAllItems();
        if(!pictures.isEmpty()){
            for(Pictures p:pictures){
                if(p.getCategoryName().equals("Pictures")&&chat_id.equals(p.getChatId())) {
                    sendPhoto.setChatId(chat_id.toString());
                    sendPhoto.setPhoto(new InputFile(p.getPictureFileId()));
                    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                    inlineKeyboardButton1.setText("Delete");
                    inlineKeyboardButton1.setCallbackData(p.getUpdateId().toString());
                    System.out.println(p.getUpdateId().toString());
                    List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
                    keyboardButtonsRow.add(inlineKeyboardButton1);
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(keyboardButtonsRow);
                    inlineKeyboardMarkup.setKeyboard(rowList);
                    sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
                    try {
                        execute(sendPhoto);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            SendMessage sm = new SendMessage();
            sm.setText("You have no saved pictures");
            sm.setChatId(update.getMessage().getChatId().toString());
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveDocument(Update update) {
        String fileId = update.getMessage().getDocument().getFileId();
        Document document = new Document();
        document.setCategoryName("Documents");
        document.setDocumentFileId(fileId);
        document.setUpdateId(update.getUpdateId());
        document.setChatId(update.getMessage().getChatId());
        documentInterface.save(document);
        String message1 = "Saved!";
        SendMessage sm = new SendMessage();
        sm.setText(message1);
        sm.setChatId(update.getMessage().getChatId().toString());
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void showDocuments(Update update, SendDocument sendDocument) {
        Long chat_id = update.getMessage().getChatId();
        List<Document> documents = documentInterface.getAllItems();
        if(!documents.isEmpty()){
            for (Document d: documents) {
                if(d.getCategoryName().equals("Documents")&&chat_id.equals(d.getChatId())){
                    sendDocument.setChatId(chat_id.toString());
                    sendDocument.setDocument(new InputFile(d.getDocumentFileId()));
                    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                    inlineKeyboardButton1.setText("Delete");
                    inlineKeyboardButton1.setCallbackData(d.getUpdateId().toString());
                    System.out.println(d.getUpdateId().toString());
                    List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
                    keyboardButtonsRow.add(inlineKeyboardButton1);
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(keyboardButtonsRow);
                    inlineKeyboardMarkup.setKeyboard(rowList);
                    sendDocument.setReplyMarkup(inlineKeyboardMarkup);
                }
               try {
                    execute(sendDocument);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else {
            SendMessage sm = new SendMessage();
            sm.setText("You have no saved documents");
            sm.setChatId(update.getMessage().getChatId().toString());
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessageCreate(Update update) {
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

    private String handleString(Update update) {
        String message = update.getMessage().getText().trim();
        //заменить обычные слова на команды
        switch (message) {
            case "Help ❓":
                message = "/help";
                break;
            case "Home":
                message = "/start";
                break;
            case "Collections \uD83D\uDCC1":
                message = "/getcategories";
                break;
            case "New collection":
                message = "/savecategory";
                break;
            case "New note":
                message = "/savenote";
                break;
            case "New picture":
                message = "/savepicture";
                break;
            case "New document":
                message = "/savedocument";
                break;
        }
        return message;
    }


    private void updateUserStatus(Update update, String message) {
        //обработка команд
        if (message.startsWith(PREFIX)) {
            if (tgUser.isActive()) {
                tgUser.setActive(false);
            }
            String thisCommand = message.split(" ")[0].toLowerCase();
            commandList.processWrongMessages(thisCommand).executeCommand(update);
            System.out.println("command detected");
            if (update.getMessage().getText().equals("/savecategory") || (update.getMessage().getText().equals("New collection"))) {
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
        }
        else {
            //default keyboard + help with commands
            //TODO method for default keyboard
            commandList.processWrongMessages(HELP.getNameOfCommand()).executeCommand(update);
        }
    }

    private SendMessage getSendMessageStart(Update update) {
        SendMessage sm = new SendMessage();
        sm.setChatId(update.getMessage().getChatId().toString());
        sm.setText("Keep your notes and files saved \uD83D\uDDD3 ");
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        row.add("Help ❓");
        row.add("Collections \uD83D\uDCC1");
        row2.add("New collection");
        row2.add("New note");
        row3.add("Pictures \uD83D\uDDBC");
        row3.add("Documents \uD83D\uDCDA");
        keyboard.add(row);
        keyboard.add(row2);
        keyboard.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sm.setReplyMarkup(replyKeyboardMarkup);
        return sm;
    }

    private SendMessage getSendMessageCategories(Update update) {
        Long chat_id = update.getMessage().getChatId();
        SendMessage sm = new SendMessage();
        sm.setChatId(update.getMessage().getChatId().toString());
        List<Category> categories = categoryInterface.getAllCategories();
        List<InlineKeyboardButton> keyboardButtonsRow_1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow_2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow_3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow_4 = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList_new = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        if (!categories.isEmpty()) {
            sm.setText("\uD83E\uDDA9 Chose one of the collections: \uD83E\uDDA9");
            for (Category category : categories) {
                if (chat_id.equals(category.getChatId())) {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(category.getCategoryName());
                    button.setCallbackData(category.getCategoryName());
                    //сделать кнопки друг под другом
                    if (keyboardButtonsRow_1.size() <= 2) {
                        keyboardButtonsRow_1.add(button);
                    } else if (keyboardButtonsRow_2.size() <= 2) {
                        keyboardButtonsRow_2.add(button);
                    } else if (keyboardButtonsRow_3.size() <= 2) {
                        keyboardButtonsRow_3.add(button);
                    } else {
                        keyboardButtonsRow_4.add(button);
                    }
                }
            }
            rowList_new.add(keyboardButtonsRow);
            rowList_new.add(keyboardButtonsRow_1);
            rowList_new.add(keyboardButtonsRow_2);
            rowList_new.add(keyboardButtonsRow_3);
            rowList_new.add(keyboardButtonsRow_4);
            inlineKeyboardMarkup.setKeyboard(rowList_new);
            sm.setReplyMarkup(inlineKeyboardMarkup);
            //кнопки меню

        } else {
            sm.setText("You have no collections");
        }
        return sm;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    //удаляем конкретную заметку
    public void deleteNote(String callbackData, String chatId) {

        List<Note> notes = noteInterface.getAllNotes();
        for (Note n : notes) {
            if (n.getUpdateId().toString().equals(callbackData)) {
                noteInterface.delete(n);
            }
        }
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText("This note was deleted!");
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void deleteDocument (String callbackData, String chatId) {
        List<Document> documents = documentInterface.getAllItems();
        for (Document d: documents) {
            if (d.getUpdateId().toString().equals(callbackData)) {
                documentInterface.delete(d);
            }
        }
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText("This document was deleted!");
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void deletePicture (String callbackData, String chatId) {
        List<Pictures> pictures = pictureInterface.getAllItems();
        for (Pictures p: pictures) {
            if (p.getUpdateId().toString().equals(callbackData)) {
                pictureInterface.delete(p);
            }
        }
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText("This picture was deleted!");
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    //универсальный метод, который получает на вход значение коллекции и чат айди и ищет все заметки,
    // сохраненные для данной коллеции
    public void getNotesForCategory(String callbackData, String chatId) {
        SendMessage sm = new SendMessage();
        List<Note> notes = noteInterface.getAllNotes();
        List<String> values = new ArrayList<>();
        for (Note n : notes) {
            if(chatId.equals(n.getChatId().toString())){
                values.add(n.getCategoryName());
                System.out.println(n);
            }
        }
        System.out.println(values);
        if (values.contains(callbackData) && !notes.isEmpty()) {
            //condition: only this collection name and only for this user (based on chatId)
            for (Note note : notes) {
                if (callbackData.equals(note.getCategoryName()) && chatId.equals(note.getChatId().toString())) {
                    sm.setChatId(chatId);
                    sm.setText(note.getNoteText());
                    InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                    inlineKeyboardButton1.setText("Delete");
                    inlineKeyboardButton1.setCallbackData(note.getUpdateId().toString());
                    System.out.println(inlineKeyboardButton1.getCallbackData());
                    List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
                    keyboardButtonsRow.add(inlineKeyboardButton1);
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(keyboardButtonsRow);
                    inlineKeyboardMarkup.setKeyboard(rowList);
                    sm.setReplyMarkup(inlineKeyboardMarkup);
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
            sm.setText("Nothing found");
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private SendMessage prepareCategoriesToDelete(Update update) {
        Long chat_id = update.getMessage().getChatId();
        SendMessage sm = new SendMessage();
        sm.setChatId(update.getMessage().getChatId().toString());
        List<Category> categories = categoryInterface.getAllCategories();
        List<InlineKeyboardButton> keyboardButtonsRow_1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow_2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow_3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow_4 = new ArrayList<>();

        List<List<InlineKeyboardButton>> rowList_new = new ArrayList<>();
        if (!categories.isEmpty()) {
            sm.setText("Chose the collection to delete ❌:");
            for (Category category : categories) {
                if (chat_id.equals(category.getChatId())) {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText("❌ "+category.getCategoryName());
                    button.setCallbackData("❌ "+category.getCategoryName());
                    //сделать кнопки друг под другом
                    if (keyboardButtonsRow_1.size() <= 2) {
                        keyboardButtonsRow_1.add(button);
                    } else if (keyboardButtonsRow_2.size() <= 2) {
                        keyboardButtonsRow_2.add(button);
                    } else if (keyboardButtonsRow_3.size() <= 2) {
                        keyboardButtonsRow_3.add(button);
                    } else {
                        keyboardButtonsRow_4.add(button);
                    }
                }
            }
            rowList_new.add(keyboardButtonsRow);
            rowList_new.add(keyboardButtonsRow_1);
            rowList_new.add(keyboardButtonsRow_2);
            rowList_new.add(keyboardButtonsRow_3);
            rowList_new.add(keyboardButtonsRow_4);
            inlineKeyboardMarkup.setKeyboard(rowList_new);
            sm.setReplyMarkup(inlineKeyboardMarkup);

        } else {
            sm.setText("You have no collections");
        }
        return sm;

    }
    public void deleteCollection(String callbackData, String chatId) {
        List<Category> categories = categoryInterface.getAllCategories();
        for (Category c : categories) {
            if(chatId.equals(c.getChatId().toString())){
                String collection = callbackData.substring(2);
                if (c.getCategoryName().equals(collection)) {
                    categoryInterface.delete(c);
                }
            }
        }
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText("This category was deleted!");
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//++ клавиатура на удаление коллекции
//List<KeyboardRow> keyboard = new ArrayList<>();
//    KeyboardRow row = new KeyboardRow();
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(false);
//        row.add("Delete collection");
//        row.add("Home");
//        keyboard.add(row);
//        replyKeyboardMarkup.setKeyboard(keyboard);
//        sm.setReplyMarkup(replyKeyboardMarkup);
}
