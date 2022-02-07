package com.teleBot.springboot.commands;

import com.teleBot.springboot.servicesAndControllers.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command{
    public static final String HELP_MSG = "✨Available commands✨" +
            "\n" +
            "/savecategory"+
            "\n"+
            "/getcategories"+
            "\n"+
            "/savenote"+
            "\n"+
            "/help"+
            "\n"+
            "/savedocument"+
            "\n"+
            "/savepicture"+
            "\n"+
            "/deletecollection";

    private final SendMessageService sendMessageService;

    public HelpCommand (SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void executeCommand(Update update) {
            sendMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MSG);

        }

}
