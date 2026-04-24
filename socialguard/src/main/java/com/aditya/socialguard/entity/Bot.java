package com.aditya.socialguard.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String personaDescription;
}
