package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface CategoryInterface {
    //save new category in DB

    void save (Category category);

    void delete (Category category);


    List<Category> getAllCategories();

}
