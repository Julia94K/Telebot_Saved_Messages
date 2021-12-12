package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Category;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

//данная команда выдает список сохраненных в базе категорий

public class GetCategoriesCommand implements Command{
    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;
    public final static String INFO_CATEGORIES = "List of categories:";

    public GetCategoriesCommand(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.categoryInterface = categoryInterface;
    }

    @Override

    public void executeCommand(Update update){
        List<Category> categories = categoryInterface.getAllCategories();
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),INFO_CATEGORIES);
        if(!categories.isEmpty()){
            for (Category category: categories){
                sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),category.getCategoryName());
            }
        } else sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),"List of categories is empty");

    }

}
