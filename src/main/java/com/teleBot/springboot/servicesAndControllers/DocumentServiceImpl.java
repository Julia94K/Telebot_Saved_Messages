package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.MediaDocument;
import com.teleBot.springboot.repository.entity.MediaDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Controller
@Service
public class DocumentServiceImpl implements DocumentService {

    MediaDocumentRepository mediaDocumentRepository;

    @Autowired
    public DocumentServiceImpl(MediaDocumentRepository mediaDocumentRepository) {
        this.mediaDocumentRepository = mediaDocumentRepository;
    }

    @Override
    public void save(MediaDocument mediaDocument){
        mediaDocumentRepository.save(mediaDocument);
    }

    @Override
    public void delete (MediaDocument mediaDocument){
        mediaDocumentRepository.delete(mediaDocument);
    }

    @Override
    public List<MediaDocument> getDocuments(){
        return mediaDocumentRepository.findAll();
    }
}
