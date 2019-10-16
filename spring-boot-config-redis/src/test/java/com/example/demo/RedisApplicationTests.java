package com.example.demo;

import com.example.demo.config.RedisConfig;
import com.example.demo.entity.AccountEntity;
import com.example.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisApplicationTests {
    @Autowired
    private AccountService accountService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void redisTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

        //未使用RedisCallback或SessionCallback
		/*redisTemplate.opsForValue().set("key1", "value1");
		redisTemplate.opsForValue().set("key2", "value2");
		redisTemplate.opsForHash().put("hash", "filed", "hvalue");*/

        //使用RedisCallback
        //useRedisCallback(redisTemplate);

        //使用SessionCallback
        useSessionCallback(redisTemplate);
    }

    //使用RedisCallback接口
    public void useRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisConnection redisConnection) -> {
            redisConnection.set("key1".getBytes(), "value1".getBytes());
            redisConnection.hSet("hash".getBytes(), "filed".getBytes(), "value".getBytes());
            return null;
        });
    }

    //使用SessionCallback
    public void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("key2", "value2");
                redisOperations.opsForHash().put("hash2", "filed2", "value2");
                return null;
            }
        });
    }

    @Test
    public void account() {
        AccountEntity accountEntity = accountService.getAccount(7);
        //log.info("123{}", accountEntity.getName());
    }

    @Test
    public void cacheTest() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName("Kyle");
        accountEntity.setMoney(200);
        accountService.saveToRedis(accountEntity);
        //log.info("123{}", accountEntity.getName());
    }

}

