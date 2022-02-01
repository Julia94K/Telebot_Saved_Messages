package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.Document;
import com.teleBot.springboot.repository.entity.DocumentRepository;
import com.teleBot.springboot.repository.entity.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DocumentFunction implements DocumentInterface {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentFunction(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void save(Document document) {
        documentRepository.save(document);
    }

    @Override
    public void delete(Document document) {
        documentRepository.delete(document);
    }

    @Override
    public List<Document> getAllItems() {
        return documentRepository.findAll();

    }

}
