package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

//дописать

public class GetNotesForCategoryCommand implements Command{

//    public static final String START_MSG = "Hello let's start!";
    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;

    public GetNotesForCategoryCommand(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.categoryInterface = categoryInterface;
    }
    @Override
    public void executeCommand(Update update){

    }
}
