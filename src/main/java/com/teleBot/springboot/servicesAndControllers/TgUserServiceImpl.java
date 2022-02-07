package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.TgUser;
import com.teleBot.springboot.repository.entity.TgUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Controller
public class TgUserServiceImpl implements TgUserService {

    private final TgUserRepository tgUserRepository;

    @Autowired
    public TgUserServiceImpl(TgUserRepository tgUserRepository) {
        this.tgUserRepository = tgUserRepository;
    }

    @Override
    public void save(TgUser tgUser){
        tgUserRepository.save(tgUser);
    }

    @Override
    public List<TgUser>getAllActiveUsers(){
        return tgUserRepository.findAllByActiveTrue();
    }
    @Override
    public Optional<TgUser>findUserByChatId(String chatId){
        return tgUserRepository.findById(chatId);
    }



}
