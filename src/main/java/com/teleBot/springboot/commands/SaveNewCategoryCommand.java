package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.CategoryService;
import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SaveNewCategoryCommand implements Command{
    public static final String ADD = "Please enter a name of the collection:";
    private final SendMessageService sendMessageService;
    private final CategoryService categoryService;

    public SaveNewCategoryCommand(SendMessageService sendMessageService, CategoryService categoryService){
        this.categoryService = categoryService;
        this.sendMessageService = sendMessageService;
    }

//сохранение новой категории в табличку: вызвать команду, ввести название категории
    @Override
    public void executeCommand(Update update) {
        //бот просит пользователя ввести название коллекции
        sendMessageService.sendMessage(update.getMessage().getChatId().toString(),ADD);
        //обработка команды происходит в классе UserMessage

    }






}
