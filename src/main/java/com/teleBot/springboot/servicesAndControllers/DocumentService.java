package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.MediaDocument;

import java.util.List;

public interface DocumentService {

    void save(MediaDocument mediaDocument);
    void delete (MediaDocument mediaDocument);
    List<MediaDocument> getDocuments();
}
