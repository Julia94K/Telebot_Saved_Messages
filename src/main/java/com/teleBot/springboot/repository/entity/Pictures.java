package com.teleBot.springboot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "picture")
//pictures
public class Pictures {

    @Id
    @Column(name = "update_id")
    private Integer updateId;

    @Column(name = "chat_id")
    private  Long chatId;

    @Column(name = "note_category")
    private String categoryName;

//    @Column(name = "note_file")
//    private Serializable document;

//    public byte[] getPicture() {
//        return picture;
//    }
//
//    public void setPicture(byte[] picture) {
//        this.picture = picture;
//    }
//
//    @Column(name = "note_picture")
//    private byte[] picture;

    @Column(name = "file_id_picture")
    private String pictureFileId;

    public String getPictureFileId() {
        return pictureFileId;
    }

    public void setPictureFileId(String pictureFileId) {
        this.pictureFileId = pictureFileId;
    }


//    @Column(name = "file_id_document")
//    private String documentFileId;
//    public String getDocumentFileId() {
//        return documentFileId;
//    }
//
//    public void setDocumentFileId(String documentFileId) {
//        this.documentFileId = documentFileId;
//    }


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

//    public Serializable getDocument() {
//        return document;
//    }
//
//    public void setDocument(Serializable document) {
//        this.document = document;
//    }
}
