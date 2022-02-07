package com.teleBot.springboot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "media_document")
public class MediaDocument extends Media{
    @Id
    @Column(name = "update_id")
    private Integer updateId;

    @Column(name = "file_id_document")
    private String fileIdDocument;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getFileIdDocument() {
        return fileIdDocument;
    }

    public void setFileIdDocument(String fileIdPicture) {
        this.fileIdDocument = fileIdPicture;
    }
}
