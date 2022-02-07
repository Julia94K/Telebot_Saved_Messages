package com.teleBot.springboot.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "media_files")
public class Media {
    @Id
    @Column(name = "update_id")
    private Integer updateId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "chat_id")
    private long chatId;

    @ManyToOne
    private Category category;

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

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

}
