package com.teleBot.springboot.commands;

public enum NameOfCommand {

    START("/start"),
    STOP ("/stop"),
    HELP("/help"),
    NOT(""),
    GET_CATEGORY("/getcategories"),
    GET_CATEGORY_TEXT("Collections"),
    SAVE_CATEGORY("/savecategory"),
    SAVE_NOTE("/savenote"),
    GET_NOTE("/getnotes"),
    EDU("/education");



    private final String nameOfCommand;

    NameOfCommand(String nameOfCommand) {
        this.nameOfCommand = nameOfCommand;
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }
}
