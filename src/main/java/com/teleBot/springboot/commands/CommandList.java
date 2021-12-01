package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;

import java.util.HashMap;

public class CommandList {
    private final HashMap<String,Command> commandMap = new HashMap<>();
    private final Command unknownCommand;

    public CommandList(SendMessageInterface sendMessageInterface){

        //add here all commands

        commandMap.put(StartCommand.START_MSG,new StartCommand(sendMessageInterface));

        unknownCommand = new UnknownCommand(sendMessageInterface);

    }

    //обработка сообщений, которые не являются командами, ,будет браться дефолтное значение unknownCommand

    public Command processWrongMessages(String wrongMessage){
        return commandMap.getOrDefault(wrongMessage,unknownCommand);
    }

}
