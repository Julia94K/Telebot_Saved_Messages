package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.Category;
import com.teleBot.springboot.repository.entity.Note;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;
@Controller
public class UserServiceImpl implements UserService {

    private static InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
    private static HashMap<Long, Integer> userStatus = new HashMap<>();
    private static List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
    private static List<List<InlineKeyboardButton>> back = new ArrayList<>();

    //обработка сообщений от пользователя
    //клавиатура
    //обработка нажатий пользователя на клавиатуру


    private final SendMessageService sendMessageService;
    private final CategoryService categoryService;
    private final NoteService noteService;
    //    private final TgUserInterface tgUserInterface;
    public static final String SAVED_MSG = "This collection was saved!";

    public UserServiceImpl(SendMessageService sendMessageService, CategoryService categoryService,
                           NoteService noteService) {
        this.categoryService = categoryService;
        this.sendMessageService = sendMessageService;
        this.noteService = noteService;
    }

    @Override

    //сохранение категории в БД
    public void proceedSimpleMessage(Update update) {

        String text = update.getMessage().getText();
        Category category = new Category();
        List<Category> categories = categoryService.getAllCategories();
        List<String> values = new ArrayList<>();
        Integer updateId = update.getUpdateId();
        Long chatId = update.getMessage().getChatId();
        category.setCategoryName(text);
        category.setUpdateId(updateId);
        category.setChatId(chatId);
        //check if this value is unique, else show message "Already exists"!
        for (Category c : categories) {
            if(chatId.equals(c.getChatId())){
                values.add(c.getCategoryName());
            }
        }
        if (!values.contains(text)) {
            //TODO поставить проверку на количество категорий в другое место (до сообщения добавьте коллекцию)
            if(values.size()<=11){
                categoryService.save(category);
                sendMessageService.sendMessage(update.getMessage().getChatId().toString(), SAVED_MSG);
            } else {
                sendMessageService.sendMessage(update.getMessage().getChatId().toString(),
                        "The maximum number of collection is reached (>12)");
            }
        } else {
            sendMessageService.sendMessage(update.getMessage().getChatId().toString(), "Category " +
                    text.toUpperCase() + " already exists");
        }
    }

    //сохранение заметки в БД ч1

    public void proceedNote(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        Note note = new Note();
        List<Note> notes = noteService.getAllNotes();
        //проверить, есть ли уже в базе строчка с пустым текстом, есди да - удалить ее.
        for (Note n : notes) {
            if (n.getNoteText()==null && n.getChatId().equals(chatId)) {
                noteService.delete(n);
            }
        }
        Integer updateId = update.getUpdateId();
        note.setCategoryName(text);
        note.setUpdateId(updateId);
        note.setChatId(chatId);

//        note.setNoteText(chatId.toString());
        noteService.save(note);
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), "Add the text of the note");


    }


    //сохранение заметки в БД ч2
    //TODO нужно полностью обнулять запись без текста, если пользователь выбрал любую другую команду
    //можно через кнопку отмена или просто затирать принудительно созданную запись

    public void saveNoteText(Update update) {
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        List<Note> notes = noteService.getAllNotes();
        for (Note note : notes) {
            if (note.getNoteText() == null && chatId.equals(note.getChatId().toString())) {
                note.setNoteText(text);
                noteService.save(note);
            }
        }
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(), "This note was added");
    }

//    public void saveFile(Update update){
//        Document docu = update.getMessage().getDocument();
//        Note note = new Note();
////        note.setDocument(docu);
//        noteInterface.save(note);
//        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), "This document was added");
//    }

//    public void savePicture(Update update){
//        PhotoSize photo = update.getMessage().getPhoto();
//
//    }
}


