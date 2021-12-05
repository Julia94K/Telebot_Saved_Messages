package com.teleBot.springboot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//extends CrudRepository<Category,String>
@Repository
public interface CategoriesRepository extends JpaRepository<Category,String> {
    //возможно стоит реализовать здесь какой-то метод
}
