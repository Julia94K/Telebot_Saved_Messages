package com.teleBot.springboot.other;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface User {
    void proceedSimpleMessage(Update update);

}
