package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Category;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class SaveNoteCommand implements Command {

    private static InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    String ADD_NOTE = "Select a category:";
    private final SendMessageInterface sendMessageInterface;
    private final NoteInterface noteInterface;
    private final CategoryInterface categoryInterface;

    public SaveNoteCommand(SendMessageInterface sendMessageInterface, NoteInterface noteInterface,
                           CategoryInterface categoryInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.noteInterface = noteInterface;
        this.categoryInterface = categoryInterface;
    }


    @Override
    public void executeCommand(Update update){
        SendMessage sm = new SendMessage();
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), ADD_NOTE);
        Long chat_id = update.getMessage().getChatId();
        List<Category> categories = categoryInterface.getAllCategories();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
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
            keyboard.add(row1);
            keyboard.add(row2);
            keyboard.add(row3);
            replyKeyboardMarkup.setKeyboard(keyboard);


        //обработка команлды происходит в классе UserMessage
    }



    //TODO как сделать, чтобы кнопки выгляделим нормально и запускалась логика на сохранение заметок


    }

}
