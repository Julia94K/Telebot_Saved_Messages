package com.teleBot.springboot.repository.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "telegram_user")
public class TgUser {
    @Id
    @Column (name = "chat_id")
    private String chatId;

    @Column (name = "is_active")
    private boolean active;
}
