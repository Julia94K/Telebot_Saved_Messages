package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.Pictures;
import com.teleBot.springboot.repository.entity.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PictureFunction implements PictureInterface {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureFunction(PictureRepository pictureRepository){
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void save(Pictures picture){
        pictureRepository.save(picture);
    }
    @Override
    public void delete (Pictures picture){
        pictureRepository.delete(picture);
    }

    @Override
    public List<Pictures> getAllItems(){
        return pictureRepository.findAll();

    }
}
