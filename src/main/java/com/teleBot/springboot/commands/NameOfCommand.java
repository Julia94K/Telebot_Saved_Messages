package com.teleBot.springboot.commands;

public enum NameOfCommand {

    START("/start"),
    STOP ("/stop"),
    HELP("/help"),
    NOT(""),
//    ADD("/addcategory"),
    STAT("/getstat"),
    GET("/getcategories"),
    SAVE_CATEGORY("/savecategory"),
    SAVE_NOTE("/savenote");


    private final String nameOfCommand;

    NameOfCommand(String nameOfCommand) {
        this.nameOfCommand = nameOfCommand;
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }
}
