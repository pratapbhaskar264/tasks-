package com.aditya.socialguard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final RedisTemplate<String, Object> redisTemplate;

    // 🔔 Handle notification logic
    public void handleNotification(Long userId, String message) {

        String cooldownKey = "user:" + userId + ":notif_cooldown";
        String listKey = "user:" + userId + ":pending_notifs";

        Boolean hasCooldown = redisTemplate.hasKey(cooldownKey);

        if (Boolean.TRUE.equals(hasCooldown)) {

            // 🧠 Add to pending list
            redisTemplate.opsForList().rightPush(listKey, message);

        } else {

            // 🚀 Send instantly
            System.out.println("Push Notification Sent: " + message);

            // ⏱️ Set cooldown (15 mins)
            redisTemplate.opsForValue()
                    .set(cooldownKey, "1", 15, TimeUnit.MINUTES);
        }
    }
}