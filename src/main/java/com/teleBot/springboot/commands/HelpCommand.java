package com.teleBot.springboot.commands;

import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.functions.TgUserInterface;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command{
    public static final String HELP_MSG = "Following commands are supported: /start, /stop, /help, " +
            "/addcategory, /getcategories, /savenote";
    public static final String ERROR_MSG = "User is not active";
    private final SendMessageInterface sendMessageInterface;
    private final TgUserInterface tgUserInterface;

    public HelpCommand (SendMessageInterface sendMessageInterface,TgUserInterface tgUserInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.tgUserInterface = tgUserInterface;
    }

    //тут первое условие почему-то всегда отрабатывает. Вообще нужно проверить, что для этого чат айди актив = актив

    @Override
    public void executeCommand(Update update){
        String chatId = update.getMessage().getChatId().toString();

        if(tgUserInterface.findUserByChatId(chatId).isPresent()){
            sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),HELP_MSG);
        } else {
            sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),ERROR_MSG);
        }
//        if(){} если пользователь есть в листе активных пользователей, то отправить ему в ответ сообщение
//        со списко команд
//        элс вывести пользователю сообщение о неактивном боте




    }
}
