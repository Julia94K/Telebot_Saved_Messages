package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.Category;

import java.util.List;

public interface CategoryService {
    //save new category in DB
    void save (Category category);

    void delete (Category category);

    List<Category> getAllCategories();

}
