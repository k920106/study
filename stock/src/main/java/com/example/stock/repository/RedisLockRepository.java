package com.example.stock.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisLockRepository {
    private RedisTemplate<String, String> redisTemplate;

    public RedisLockRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean lock(Long key) {
        // opsForValue -> Strings를 쉽게 Serialize / Deserialize 해주는 Interface
        // setIfAbsent -> 문자열이 없으면 key문자열 value과 만료를 유지하도록 설정합니다 .timeoutkey
        return redisTemplate.opsForValue()
                            .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(3_000));
    }

    public Boolean unlock(Long key) {
        // delete -> key 삭제
        return redisTemplate.delete(generateKey(key));
    }

    private String generateKey(Long key) {
        return key.toString();
    }
}
