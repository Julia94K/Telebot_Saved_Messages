package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;

import java.util.HashMap;
import static com.teleBot.springboot.commands.NameOfCommand.*;


public class CommandList {
    private final HashMap<String,Command> commandMap = new HashMap<>();
    private final Command unknownCommand;


//добавлен второй аргумент
    public CommandList(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface,
                       TgUserInterface tgUserInterface){

        //add here all commands

        commandMap.put(START.getNameOfCommand(),new StartCommand(sendMessageInterface,tgUserInterface));
        commandMap.put(STOP.getNameOfCommand(),new StopCommand(sendMessageInterface,tgUserInterface));
        commandMap.put(ADD.getNameOfCommand(),new AddNewCategory(sendMessageInterface, categoryInterface));//второй аргумент добавлен
        commandMap.put(HELP.getNameOfCommand(),new HelpCommand(sendMessageInterface,tgUserInterface));
        //данный метод вообще не нужен в проекте, потому что слова и заметки можно сохранять в категории
        commandMap.put(NOT.getNameOfCommand(),new NotACommand(sendMessageInterface));
        commandMap.put(STAT.getNameOfCommand(),new StatisticCommand(sendMessageInterface,tgUserInterface));
        commandMap.put(GET.getNameOfCommand(),new GetCategoriesCommand(sendMessageInterface,categoryInterface));

        unknownCommand = new UnknownCommand(sendMessageInterface);


    }


    //обработка сообщений, которые не являются командами, ,будет браться дефолтное значение unknownCommand

    public Command processWrongMessages(String wrongMessage){
        return commandMap.getOrDefault(wrongMessage,unknownCommand);
    }


}
