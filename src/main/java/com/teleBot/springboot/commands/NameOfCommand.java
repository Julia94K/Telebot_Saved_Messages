package com.teleBot.springboot.commands;

public enum NameOfCommand {

    START("/start"),
    STOP ("/stop"),
    HELP("/help"),
    NOT(""),
    ADD("/addcategory");


    private final String nameOfCommand;

    NameOfCommand(String nameOfCommand) {
        this.nameOfCommand = nameOfCommand;
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }
}
