package com.teleBot.springboot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//extends CrudRepository<Category,String>
@Repository
public interface CategoriesRepository extends JpaRepository<Category,String> {
//    @Query("select Category.categoryName from #{#categoriesRepository}")
//    List <Category> getAllNames();
}
