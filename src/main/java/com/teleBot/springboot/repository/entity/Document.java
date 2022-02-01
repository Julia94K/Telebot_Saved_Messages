package com.teleBot.springboot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "update_id")
    private Integer updateId;

    @Column(name = "chat_id")
    private  Long chatId;

    @Column(name = "note_category")
    private String categoryName;

    @Column(name = "document_file_id")
    private  String documentFileId;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDocumentFileId() {
        return documentFileId;
    }

    public void setDocumentFileId(String documentFileId) {
        this.documentFileId = documentFileId;
    }
}
