package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.NoteInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;

import java.util.HashMap;
import static com.teleBot.springboot.commands.NameOfCommand.*;


public class CommandList {
    private final HashMap<String,Command> commandMap = new HashMap<>();
    private final Command unknownCommand;


//добавлен второй аргумент
    public CommandList(SendMessageInterface sendMessageInterface, TgUserInterface tgUserInterface, CategoryInterface categoryInterface,
                       NoteInterface noteInterface){

        //add here all commands
        commandMap.put(START.getNameOfCommand(),new StartCommand(sendMessageInterface));
//        commandMap.put(STOP.getNameOfCommand(),new StopCommand(sendMessageInterface,tgUserInterface));
        commandMap.put(HELP.getNameOfCommand(),new HelpCommand(sendMessageInterface));
        commandMap.put(NOT.getNameOfCommand(),new NotACommand(sendMessageInterface));
        commandMap.put(GET_CATEGORY.getNameOfCommand(),new GetCategoriesCommand(sendMessageInterface));
        commandMap.put(SAVE_CATEGORY.getNameOfCommand(),new SaveNewCategoryCommand(sendMessageInterface,categoryInterface));
        commandMap.put(SAVE_NOTE.getNameOfCommand(),new SaveNoteCommand(sendMessageInterface));
        commandMap.put(DELETE.getNameOfCommand(),new DeleteCollectionCommand(sendMessageInterface));
//        commandMap.put(SAVE_NOTE_TEXT.getNameOfCommand(),new SaveNoteCommand(sendMessageInterface));
        commandMap.put(GET_NOTE.getNameOfCommand(),new GetNotesForCategoryCommand(sendMessageInterface,categoryInterface,noteInterface));
        commandMap.put(DOCUMENT.getNameOfCommand(),new SaveDocuCommand(sendMessageInterface));
        commandMap.put(PICTURE.getNameOfCommand(),new SavePictureCommand(sendMessageInterface));
        unknownCommand = new UnknownCommand(sendMessageInterface);


    }


    //обработка сообщений, которые не являются командами, ,будет браться дефолтное значение unknownCommand

    public Command processWrongMessages(String wrongMessage){
        return commandMap.getOrDefault(wrongMessage,unknownCommand);
    }


}
