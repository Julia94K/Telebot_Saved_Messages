package com.teleBot.springboot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoteRepository extends JpaRepository <Note,String>  {

    List<Note>findAllByCategoryName(String categoryName);
}
