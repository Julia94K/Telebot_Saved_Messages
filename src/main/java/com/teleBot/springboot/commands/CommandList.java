package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import java.util.HashMap;
import static com.teleBot.springboot.commands.NameOfCommand.*;


public class CommandList {
    private final HashMap<String,Command> commandMap = new HashMap<>();
    private final Command unknownCommand;



    public CommandList(SendMessageInterface sendMessageInterface){

        //add here all commands

        commandMap.put(START.getNameOfCommand(),new StartCommand(sendMessageInterface));
        commandMap.put(STOP.getNameOfCommand(),new StopCommand(sendMessageInterface));
        commandMap.put(ADD.getNameOfCommand(),new AddNewCategory(sendMessageInterface));
        commandMap.put(HELP.getNameOfCommand(),new HelpCommand(sendMessageInterface));
        commandMap.put(NOT.getNameOfCommand(),new NotACommand(sendMessageInterface));

        unknownCommand = new UnknownCommand(sendMessageInterface);


    }


    //обработка сообщений, которые не являются командами, ,будет браться дефолтное значение unknownCommand

    public Command processWrongMessages(String wrongMessage){
        return commandMap.getOrDefault(wrongMessage,unknownCommand);
    }


}
