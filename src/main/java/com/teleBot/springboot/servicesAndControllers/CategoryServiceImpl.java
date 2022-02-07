package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.CategoriesRepository;
import com.teleBot.springboot.repository.entity.Category;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Service
public class CategoryServiceImpl implements CategoryService {
    //создаем объект типа репозиторий
    private final CategoriesRepository categoriesRepository;

    //реализуем конструктор
    @Autowired
    public CategoryServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    //сохранение категорий в табличку DB
    @Override

    public void save (Category category) {
        categoriesRepository.save(category);
    }

    @Override
    public void delete (Category category){
        categoriesRepository.delete(category);
    }

    //получение из БД списка всех категорий. Переписать этот метод так, чтобы он возвращал только имена категорий
    //step 2: имена категорий возвращаются в виде кликабельных кнопок

    @Override
    public List<Category>getAllCategories(){
        return categoriesRepository.findAll();

    }


}
