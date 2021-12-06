package com.teleBot.springboot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "note")
public class Note {
    //chat_id - связь с таблицей telegram_user
    @Id
    @Column(name = "update_id")
    private Integer updateId;

    //значение категории (связь с таблицей category)
    @Column(name = "note_category")
    private String categoryName;

    @Column(name = "note_name")
    private String noteName;

    //пока упрощенно добавляем только возможность сохранять текст в БД
    @Column(name="note_text")
    private String noteText;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer chatId) {
        this.updateId = chatId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
