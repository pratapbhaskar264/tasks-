package com.aditya.socialguard.service;

import com.aditya.socialguard.entity.Comment;
import com.aditya.socialguard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RedisService redisService;
    private final NotificationService notificationService;

    public Comment addComment(Comment comment) {

        //  Vertical cap
        if (comment.getDepthLevel() > 20) {
            throw new RuntimeException("Depth limit exceeded");
        }

        boolean isBot = comment.getAuthorId() > 1000;

        if (isBot) {

            //  Atomic horizontal cap
            Long count = redisService.incrementBotCount(comment.getPostId());
            if (count > 100) {
                throw new RuntimeException("Bot limit exceeded (100 max)");
            }

            //  Cooldown
            if (redisService.isCooldownActive(comment.getAuthorId(), 1L)) {
                throw new RuntimeException("Cooldown active");
            }

            redisService.setCooldown(comment.getAuthorId(), 1L);

            //  Virality +1
            redisService.incrementVirality(comment.getPostId(), 1);

        } else {

            //  Human comment +50
            redisService.incrementVirality(comment.getPostId(), 50);
        }

        notificationService.handleNotification(
                1L, // dummy user for now
                "Bot " + comment.getAuthorId() + " replied to your post"
        );

        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }
}