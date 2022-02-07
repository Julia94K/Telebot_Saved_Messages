package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.TgUser;

import java.util.List;
import java.util.Optional;

public interface TgUserService {
    void save (TgUser tgUser);
    List<TgUser>getAllActiveUsers();
    Optional<TgUser>findUserByChatId(String chatId);
}
