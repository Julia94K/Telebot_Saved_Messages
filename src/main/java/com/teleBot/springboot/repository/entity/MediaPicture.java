package com.teleBot.springboot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "media_picture")
public class MediaPicture extends Media{
    @Id
    @Column(name = "update_id")
    private Integer updateId;

    @Column(name = "file_id_picture")
    private String fileIdPicture;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getFileIdPicture() {
        return fileIdPicture;
    }

    public void setFileIdPicture(String fileIdPicture) {
        this.fileIdPicture = fileIdPicture;
    }
}
