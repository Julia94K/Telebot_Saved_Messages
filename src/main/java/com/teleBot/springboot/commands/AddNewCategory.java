package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Category;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Scanner;

public class AddNewCategory implements Command {
    public static final String ADD_MSG = "You can add new category!";
    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;//added

    //one parameter added to the constructor
    public AddNewCategory(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.categoryInterface = categoryInterface; //added
    }
    //was
//    public AddNewCategory(SendMessageInterface sendMessageInterface) {
//        this.sendMessageInterface = sendMessageInterface;
//    }

    @Override
    public void executeCommand(Update update){
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),ADD_MSG);
        Scanner in = new Scanner(System.in);
        String text = in.next();
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),text);
        //сохранение. Как сделать чтобы в поле категория сохранялся верное значение, которое ввел пользователь
        Category category = new Category();
        category.setCategoryId(update.getUpdateId().toString());
        category.setCategoryName(update.getMessage().getText());
        categoryInterface.save(category);

//        System.out.println("New catgory"+text+"was saved!");
//        Category category = new Category();
//        category.setCategoryId(update.getUpdateId().toString());
//        category.setCategoryName(update.getMessage().getText());
//        category.setCategoryName(update.getMessage().getText().
//        addCategoryInterface.save(category);
//        Scanner in = new Scanner(System.in);
//        String category2 = in.next();
//
//        System.out.println("New category "+category2+"was added!");
//        addCategoryInterface.saveCategory(category1);
//        addCategoryInterface.saveCategory(ca);

    }




}
