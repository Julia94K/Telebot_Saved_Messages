package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.commands.NameOfCommand.*;
import java.util.HashMap;

import static com.teleBot.springboot.commands.NameOfCommand.START;


public class CommandList {
    private final HashMap<String,Command> commandMap = new HashMap<>();
    private final Command unknownCommand;


    private String commandName;


    public CommandList(SendMessageInterface sendMessageInterface){

        //add here all commands

        commandMap.put(START.getNameOfCommand(),new StartCommand(sendMessageInterface));

        unknownCommand = new UnknownCommand(sendMessageInterface);

    }

//    public Command processCommands

    //обработка сообщений, которые не являются командами, ,будет браться дефолтное значение unknownCommand

    public Command processWrongMessages(String wrongMessage){
        return commandMap.getOrDefault(wrongMessage,unknownCommand);
    }

//    public Command answerUser(Command command){
//        if (CommandMap.)
//        }

}
