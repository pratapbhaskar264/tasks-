package com.aditya.socialguard.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long authorId; // can be user or bot

    @Column(length = 2000)
    private String content;

    private LocalDateTime createdAt;
}
