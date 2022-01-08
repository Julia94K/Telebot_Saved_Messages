package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class HelpCommand implements Command{
    public static final String HELP_MSG = "✨Дотупные команды✨" +
            "\n" +
            "/savecategory"+
            "\n"+
            "/getcategories"+
            "\n"+
            "/savenote"+
            "\n"+
            "/getnotes"+
            "\n"+
            "/help";
    private final SendMessageInterface sendMessageInterface;

    public HelpCommand (SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }

    @Override
    public void executeCommand(Update update) {
            sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), HELP_MSG);

        }

}
