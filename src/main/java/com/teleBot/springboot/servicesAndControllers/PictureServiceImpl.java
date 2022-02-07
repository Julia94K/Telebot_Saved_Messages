package com.teleBot.springboot.servicesAndControllers;

import com.teleBot.springboot.repository.entity.MediaPicture;
import com.teleBot.springboot.repository.entity.MediaPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Controller
@Service
public class PictureServiceImpl implements PictureService {

    MediaPictureRepository mediaPictureRepository;

    @Autowired
    public PictureServiceImpl(MediaPictureRepository mediaPictureRepository) {
        this.mediaPictureRepository = mediaPictureRepository;
    }

    @Override
    public void save(MediaPicture mediaPicture){
        mediaPictureRepository.save(mediaPicture);
    }

    @Override
    public void delete (MediaPicture mediaPicture){
        mediaPictureRepository.delete(mediaPicture);
    }

    @Override
    public List<MediaPicture> getPictures(){
        return mediaPictureRepository.findAll();
    }
}
