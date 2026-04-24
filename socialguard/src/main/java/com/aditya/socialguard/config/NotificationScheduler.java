package com.aditya.socialguard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final RedisTemplate<String, Object> redisTemplate;

    @Scheduled(fixedRate = 300000) // every 5 minutes
    public void processNotifications() {

        // 🔍 For simplicity, check for user:1
        Long userId = 1L;

        String listKey = "user:" + userId + ":pending_notifs";

        Long size = redisTemplate.opsForList().size(listKey);

        if (size != null && size > 0) {

            //  Pop all notifications
            List<Object> messages = redisTemplate.opsForList()
                    .range(listKey, 0, -1);

            redisTemplate.delete(listKey);

            System.out.println(
                "🔔 Summarized Notification: " +
                messages.size() + " new interactions on your posts"
            );
        }
    }
}