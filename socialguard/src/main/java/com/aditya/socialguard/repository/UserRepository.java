package com.aditya.socialguard.repository;

import com.aditya.socialguard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}