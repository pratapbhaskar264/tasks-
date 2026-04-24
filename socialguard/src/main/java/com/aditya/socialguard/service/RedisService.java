package com.aditya.socialguard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    // 🔥 Virality Score
    public void incrementVirality(Long postId, int score) {
        String key = "post:" + postId + ":virality_score";
        redisTemplate.opsForValue().increment(key, score);
    }

    // 🔥 Bot count (Horizontal cap)
    public Long incrementBotCount(Long postId) {
        String key = "post:" + postId + ":bot_count";
        return redisTemplate.opsForValue().increment(key);
    }

    // 🔥 Cooldown check
    public boolean isCooldownActive(Long botId, Long userId) {
        String key = "cooldown:bot_" + botId + ":user_" + userId;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void setCooldown(Long botId, Long userId) {
        String key = "cooldown:bot_" + botId + ":user_" + userId;
        redisTemplate.opsForValue().set(key, "1", 10, TimeUnit.MINUTES);
    }

    public boolean allowBotReply(Long postId) {
        String key = "post:" + postId + ":bot_count";

        Long count = redisTemplate.opsForValue().increment(key);

        return count <= 100;
    }
}