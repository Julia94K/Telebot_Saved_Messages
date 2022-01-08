package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Category;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

//данная команда выдает список сохраненных в базе категорий

public class GetCategoriesCommand implements Command {
    //++
//    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;
    public final static String INFO_CATEGORIES = "List of categories:";


    public GetCategoriesCommand(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.categoryInterface = categoryInterface;
    }

//оптимизировать с чат айди сейчас для другого сообщения печатается, что чат айди пустой
    @Override
    public void executeCommand(Update update) {
        List<Category> categories = categoryInterface.getAllCategories();
        Long chat_id = update.getMessage().getChatId();
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), INFO_CATEGORIES);
        for (Category category : categories) {
            if (!categories.isEmpty()) {
                if (chat_id.equals(category.getChatId())) {
                    sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), category.getCategoryName());
                }
            } else
                sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), "List of categories is empty");

        }
    }
}
