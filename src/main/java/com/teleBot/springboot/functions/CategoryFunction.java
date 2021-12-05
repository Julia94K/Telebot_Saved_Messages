package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.CategoriesRepository;
import com.teleBot.springboot.repository.entity.Category;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//логика для этого класса еще не реализована
//?нужна ли вообще отдельная логика или можно воспользоваться функцией send message
//определить, является ли данный класс контроллером или сервисом
@Controller
@Service //нужна ли тут эта аннотация
public class CategoryFunction implements CategoryInterface {


    //создаем объект типа репозиторий
    private final CategoriesRepository categoriesRepository;

    //реализуем конструктор
    @Autowired
    public CategoryFunction(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    //сохранение категорий в табличку DB
    @Override

    public void save (Category category) {
        categoriesRepository.save(category);
    }

    //получение из БД списка всех категорий

    @Override
    public List<Category>getAllCategories(){
        return categoriesRepository.findAll();
    }


}
