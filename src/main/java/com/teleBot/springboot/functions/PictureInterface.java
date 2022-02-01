package com.teleBot.springboot.functions;

import com.teleBot.springboot.repository.entity.Pictures;

import java.util.List;

public interface PictureInterface {
    void save(Pictures picture);
    void delete (Pictures picture);
    List<Pictures> getAllItems();
}
