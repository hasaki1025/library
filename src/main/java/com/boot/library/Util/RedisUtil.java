package com.boot.library.Util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisUtil {


    @Value("${spring.redis.host}")
    String RedisHost;
    @Value("${spring.redis.password}")
    String RedisPwd;
    @Value("${spring.redis.port}")
    int RedisPort;

    @Bean
    Jedis createJedis()
    {
        Jedis jedis = new Jedis(RedisHost, RedisPort);
        jedis.auth(RedisPwd);
        return jedis;
    }
}
