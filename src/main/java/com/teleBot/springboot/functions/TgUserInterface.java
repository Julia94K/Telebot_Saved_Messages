package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.TgUser;

import java.util.List;
import java.util.Optional;

public interface TgUserInterface {
    void save (TgUser tgUser);
    List<TgUser>getAllActiveUsers();
    Optional<TgUser>findUserByChatId(String chatId);
}
