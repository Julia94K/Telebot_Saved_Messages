package com.teleBot.springboot.other;
import com.teleBot.springboot.functions.CategoryInterface;
import com.teleBot.springboot.functions.SendMessageInterface;
import com.teleBot.springboot.repository.entity.Category;
import com.teleBot.springboot.repository.entity.TgUser;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UserMessage implements User {
    //обработка сообщений от пользователя
    //клавиатура
    //обработка нажатий пользователя на клавиатуру


    private final SendMessageInterface sendMessageInterface;
    private final CategoryInterface categoryInterface;
    public static final String SAVED_MSG = "This collection was saved!";

    public UserMessage(SendMessageInterface sendMessageInterface, CategoryInterface categoryInterface){
        this.categoryInterface = categoryInterface;
        this.sendMessageInterface = sendMessageInterface;
    }
    @Override
    public void proceedSimpleMessage (Update update){

        String text = update.getMessage().getText();
        Category category = new Category();
        Integer updateId = update.getUpdateId();
        category.setCategoryName(text);
        category.setUpdateId(updateId);
        categoryInterface.save(category);
        sendMessageInterface.sendMessage(update.getMessage().getChatId().toString(),SAVED_MSG);


    }
}
