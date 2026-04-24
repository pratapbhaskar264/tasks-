package com.aditya.socialguard.repository;

import com.aditya.socialguard.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotRepository extends JpaRepository<Bot, Long> {
}