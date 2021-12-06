package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Category;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SaveNewCategoryCommand implements Command{
    public static final String SAVED_MSG = "This category was saved!";
    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;

    public SaveNewCategoryCommand(SendMessageInterface sendMessageInterface,CategoryInterface categoryInterface){
        this.categoryInterface = categoryInterface;
        this.sendMessageInterface = sendMessageInterface;
    }


    @Override
    public void executeCommand(Update update) {
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),SAVED_MSG);
        Category category = new Category();
        category.setCategoryId(update.getUpdateId().toString());
        category.setCategoryName(update.getMessage().getText());
        categoryInterface.save(category);

    }
}
