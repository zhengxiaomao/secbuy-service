package com.example.secbuyservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisUtil {

    /**
     * 对指定key的键值减一
     * @param key 键
     * @return Long
     *
     *
     */

    @Autowired
    private RedisTemplate redisTemplate;

    public Long decrStock(String productName) {
      return  redisTemplate.opsForValue().decrement(productName);
    }

    public void setValue(String productName, int productStocks) {
        redisTemplate.opsForValue().set(productName,productStocks);
    }
}

