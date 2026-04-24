package com.aditya.socialguard.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private Long authorId;

    @Column(length = 2000)
    private String content;

    private int depthLevel;

    private LocalDateTime createdAt;
}