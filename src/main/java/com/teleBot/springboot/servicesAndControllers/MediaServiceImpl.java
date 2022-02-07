package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.Media;
import com.teleBot.springboot.repository.entity.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Controller
@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;


    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }


    @Override
    public void save(Media media){
        mediaRepository.save(media);
    }

    @Override
    public void delete (Media media){
        mediaRepository.delete(media);
    }

    @Override
    public List<Media> getMediaItems() {
        return mediaRepository.findAll();
    }

}
