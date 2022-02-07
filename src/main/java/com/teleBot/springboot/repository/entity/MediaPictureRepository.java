package com.teleBot.springboot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaPictureRepository extends JpaRepository<MediaPicture,String> {
}
