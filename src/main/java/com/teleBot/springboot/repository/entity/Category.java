package com.teleBot.springboot.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity // сущность для ДБ
@Table (name = "category") // имя для таблицы
public class Category {

    @Id //говорит, что следующий аттрибут - это первичный ключ
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "category_id") //имя поля таблицы ДБ
    private String categoryId;

    @Column(name="category_name")//имя поля таблицы ДБ
    private String categoryName;

    //getters and setters for future work with the values
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
