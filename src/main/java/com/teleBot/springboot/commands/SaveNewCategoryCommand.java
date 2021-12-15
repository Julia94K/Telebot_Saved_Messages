package com.teleBot.springboot.commands;

import com.teleBot.springboot.bot.MyTeleBot;
import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Category;
import com.teleBot.springboot.repository.entity.Note;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Scanner;

public class SaveNewCategoryCommand implements Command{
    public static final String ADD = "Please enter a name of the collection:";
    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;
    private String value;

    public SaveNewCategoryCommand(SendMessageInterface sendMessageInterface,CategoryInterface categoryInterface){
        this.categoryInterface = categoryInterface;
        this.sendMessageInterface = sendMessageInterface;
    }

//сохранение новой категории в табличку: вызвать команду, ввести название категории
    @Override
    public void executeCommand(Update update) {
        //бот просит пользователя ввести название коллекции
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),ADD);

        //пользователь вводит название коллекции, которую хочет сохранить
//        Scanner scanner = new Scanner(MyTeleBot.in)
//        Scanner sc = new Scanner(System.in);
//        String category = sc.next();
////        executeCommand(update.getMessage().getChatId().toString(),);

//        String textCategory = update.getMessage().getText();
//
//        Category category = new Category();
//        Integer updateId = update.getUpdateId();
//        category.setCategoryName(textCategory);
//        category.setUpdateId(updateId);
//        categoryInterface.save(category);
//        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),SAVED_MSG);

    }






}
