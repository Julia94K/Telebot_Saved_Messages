package com.teleBot.springboot.other;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import com.teleBot.springboot.repository.entity.Category;
import com.teleBot.springboot.repository.entity.Note;
import com.teleBot.springboot.repository.entity.TgUser;
import org.springframework.data.relational.core.sql.In;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserMessage implements User {

    private static InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
    private static HashMap<Long, Integer> userStatus = new HashMap<>();
    private static List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
    private static List<List<InlineKeyboardButton>> back = new ArrayList<>();

    //обработка сообщений от пользователя
    //клавиатура
    //обработка нажатий пользователя на клавиатуру



    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;
    private final NoteInterface noteInterface;
    //    private final TgUserInterface tgUserInterface;
    public static final String SAVED_MSG = "This collection was saved!";

    public UserMessage(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface,
                       NoteInterface noteInterface) {
        this.categoryInterface = categoryInterface;
        this.sendMessageInterface = sendMessageInterface;
        this.noteInterface = noteInterface;
    }

    @Override

    //сохранение категории в БД
    public void proceedSimpleMessage(Update update) {

        String text = update.getMessage().getText();
        Category category = new Category();
        Integer updateId = update.getUpdateId();
        category.setCategoryName("/" + text);
        category.setUpdateId(updateId);
        categoryInterface.save(category);
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), SAVED_MSG);


    }

    //сохранение заметки в БД ч1

    public void proceedNote(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        Note note = new Note();
        //проверить нет ли уже записи с чат айди вместо текста. Если есть, то удалить
        Integer updateId = update.getUpdateId();
        note.setCategoryName(text);
        note.setUpdateId(updateId);
        note.setChatId(chatId);
        note.setNoteText(chatId.toString());
        noteInterface.save(note);
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), "Add the text of the note");



    }

    //сохранение заметки в БД ч2
    //TODO нужно полностью обнулять запись без текста, если пользователь выбрал любую другую команду
    //можно через кнопку отмена или просто затирать принудительно созданную запись

    public void saveNoteText(Update update) {
        String text = update.getMessage().getText();
        //нужно найти строчку, в которой текст = chatId
        //try catch?
        String chatId = update.getMessage().getChatId().toString();
        List<Note> notes = noteInterface.getAllNotes();
        for (Note note : notes) {
            if (note.getNoteText().equals(chatId)) {
                note.setNoteText(text);
                noteInterface.save(note);

            }
        }
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), "This note was added");
    }
}


