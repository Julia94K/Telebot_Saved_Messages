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
//    public static final String ERROR_MSG = "User is not active";
    private final SendMessageInterface sendMessageInterface;



    public HelpCommand (SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }



    //тут первое условие почему-то всегда отрабатывает. Вообще нужно проверить, что для этого чат айди актив = актив

    @Override
    public void executeCommand(Update update) {
        String chatId = update.getMessage().getChatId().toString();
            sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(), HELP_MSG);


        }

}
