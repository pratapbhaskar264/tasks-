package com.aditya.socialguard.repository;

import com.aditya.socialguard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}