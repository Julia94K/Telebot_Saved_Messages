package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.Media;

import java.util.List;

public interface MediaService {
    void save(Media media);
    void delete (Media media);
    List<Media> getMediaItems();
}
