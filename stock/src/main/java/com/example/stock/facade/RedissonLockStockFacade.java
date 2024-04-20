package com.example.stock.facade;

import com.example.stock.service.StockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockStockFacade {
    private RedissonClient redissonClient;
    private StockService stockService;

    public RedissonLockStockFacade(RedissonClient redissonClient, StockService stockService) {
        this.redissonClient = redissonClient;
        this.stockService = stockService;
    }

    public void decrease(Long key, Long quantity) {
        RLock lock = redissonClient.getLock(key.toString());

        try {
            // 락 획득을 시도한다 (10초동안 시도를 할 예정이며 획득할 경우 1초안에 해제할 예정이다
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                System.out.println("lock 획득 실패");
                return;
            }

            stockService.decrease(key, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
