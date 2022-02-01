package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.Document;
import com.teleBot.springboot.repository.entity.Pictures;

import java.util.List;

public interface DocumentInterface {
    void save(Document document);

    void delete(Document document);

    List<Document> getAllItems();
}
