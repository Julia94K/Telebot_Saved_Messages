package com.teleBot.springboot.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity // сущность для ДБ
@Table (name = "category") // имя для таблицы
public class Category {

    @Id //говорит, что следующий аттрибут - это первичный ключ
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "update_id") //имя поля таблицы ДБ
    private Integer updateId;

    @Column(name="category_name")//имя поля таблицы ДБ
    private String categoryName;

    //getters and setters for future work with the values
    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
