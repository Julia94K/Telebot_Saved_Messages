package com.teleBot.springboot.servicesAndControllers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserService {
    void proceedSimpleMessage(Update update);

}
