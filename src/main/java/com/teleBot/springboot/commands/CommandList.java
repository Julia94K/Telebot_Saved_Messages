package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.CategoryService;
import com.teleBot.springboot.servicesAndControllers.NoteService;
import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import com.teleBot.springboot.servicesAndControllers.TgUserService;

import java.util.HashMap;
import static com.teleBot.springboot.commands.NameOfCommand.*;


public class CommandList {
    private final HashMap<String,Command> commandMap = new HashMap<>();
    private final Command unknownCommand;


//добавлен второй аргумент
    public CommandList(SendMessageService sendMessageService, TgUserService tgUserService, CategoryService categoryService,
                       NoteService noteService){

        //add here all commands
        commandMap.put(START.getNameOfCommand(),new StartCommand(sendMessageService));
        commandMap.put(HELP.getNameOfCommand(),new HelpCommand(sendMessageService));
        commandMap.put(GET_CATEGORY.getNameOfCommand(),new GetCategoriesCommand(sendMessageService));
        commandMap.put(SAVE_CATEGORY.getNameOfCommand(),new SaveNewCategoryCommand(sendMessageService, categoryService));
        commandMap.put(SAVE_NOTE.getNameOfCommand(),new SaveNoteCommand(sendMessageService));
        commandMap.put(DELETE.getNameOfCommand(),new DeleteCollectionCommand(sendMessageService));
        commandMap.put(GET_NOTE.getNameOfCommand(),new GetNotesForCategoryCommand(sendMessageService, categoryService, noteService));
        commandMap.put(DOCUMENT.getNameOfCommand(),new SaveDocuCommand(sendMessageService));
        commandMap.put(PICTURE.getNameOfCommand(),new SavePictureCommand(sendMessageService));
        unknownCommand = new UnknownCommand(sendMessageService);
    }


    //обработка сообщений, которые не являются командами, ,будет браться дефолтное значение unknownCommand
    public Command processWrongMessages(String wrongMessage){
        return commandMap.getOrDefault(wrongMessage,unknownCommand);
    }


}
