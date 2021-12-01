package com.teleBot.springboot.commands;
//импорт объекта из одной из библиотек для телеграм ботов
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void executeCommand(Update update);

}
