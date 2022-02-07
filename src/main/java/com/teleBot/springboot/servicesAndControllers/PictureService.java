package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.MediaPicture;

import java.util.List;

public interface PictureService {
    void save(MediaPicture mediaPicture);
    void delete (MediaPicture mediaPicture);
    List<MediaPicture> getPictures();
}
