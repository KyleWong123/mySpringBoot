package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@Slf4j
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;
    private Object value = null;


    /***
     *String、Hash类型测试
     * @return
     */
    @GetMapping("/teststring")
    public Map<String, Object> testStringAndHash(){
        log.info("redis存储String类型测试");
        redisTemplate.opsForValue().set("stringTest", "您好");
        //redisTemplate.opsForValue().set("int_key", "1");
        log.info("redis给指定key对应的value加指定的数值");
        redisTemplate.opsForValue().increment("int_key", 2);
        log.info("redis给指定的key对应的value减指定的值");
        redisTemplate.opsForValue().decrement("int_key", 2);
        stringRedisTemplate.opsForValue().set("int", "1");
        stringRedisTemplate.opsForValue().increment("int", 2);
        log.info("redis存储hash类型操作");
        Map<String, String> hash = new HashMap<>(4);
        hash.put("filed1", "value1");
        hash.put("filed2", "value2");
        // 存入一个hash类型，key为hashtest,values为map集合
        log.info("给指定hash key中存入多个filed、value");
        redisTemplate.opsForHash().putAll("hashtest", hash);
        log.info("给hashtest添加values");
        redisTemplate.opsForHash().put("hashtest", "filed3", "value3");
        log.info("新添加key及values");
        redisTemplate.opsForHash().put("hash", "filed", "value");
        // 绑定hash的key
        log.info("绑定hash中指定的key，方便操作");
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps("hashtest");
        boundHashOperations.put("filed4", "value4");
        boundHashOperations.delete("filed4", "filed1");
        Map<String, Object> map = new HashMap<>(4);
        map.put("Success", true);
        return map;
    }

    /***
     * List类型测试
     * @return
     */
    @GetMapping("/testlist")
    public Map<String, Object> testList(){
        log.info("redis存储list链表操作");
        log.info("从指定list链表的头部开始插入多个value");
        redisTemplate.opsForList().leftPushAll("list1", "1", "2", "3");
        log.info("从指定list链表的尾部开始插入多个value");
        redisTemplate.opsForList().rightPushAll("list2", "a", "b", "c", "d");
        log.info("获取键值为list1的值");
        List<String> list = redisTemplate.opsForList().range("list1", 0, 10);
        for (String value:list
             ) {
            log.info(value);
        }
        log.info("绑定list链表的key值，方便操作");
        BoundListOperations boundListOperations = redisTemplate.boundListOps("list1");
        log.info("从指定key的链表的头部随机取出一个值");
        value = boundListOperations.leftPop();
        log.info("本次取出的值为{}"+value);
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        return map;
    }

    /***
     * set类型测试
     * @return
     */
    @GetMapping("/testset")
    public Map<String, Object> testSet(){
        log.info("在redis数据库中插入set类型，set中没有重复元素");
        redisTemplate.opsForSet().add("set1", "a", "a", "b", "c", "d");
        redisTemplate.opsForSet().add("set2", "1", "2", "3", "4");
        log.info("绑定set1,方便操作");
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps("set1");
        log.info("在set1对应的set集合中新添加两个元素");
        boundSetOperations.add("add1", "add2");
        log.info("删除set1对应集合中的元素");
        boundSetOperations.remove("a");
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        return map;
    }

    /***
     * redis有序集合Zset测试
     * @return
     */
    @GetMapping("/testzset")
    public Map<String, Object> testZset(){
        log.info("redis的有序集合zset测试");
        log.info("向score集合中添加单个元素以及对应的score");
        redisTemplate.opsForZSet().add("score", "王帆", 0.5);
        redisTemplate.opsForZSet().add("score", "Tom", 0.7);
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        return map;
    }

    @GetMapping("/testmulti")
    public Map<String, Object> testMulti(){
        log.info("在redis数据库中添加key1键和value1值");
        redisTemplate.opsForValue().set("key1", "1");
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                log.info("redis开启事务监控key1");
                redisOperations.watch("key1");
                /*log.info("在执行事务之前让被监视的key1发生改变");
                redisOperations.opsForValue().set("key1", "88888");*/
                log.info("redis开启事务，其后的redis命令将会被存放的队列，不会立刻被执行");
                redisOperations.multi();
                redisOperations.opsForValue().set("key2", "value2");
                log.info("获取key2所对应的value应该为null");
                String value = (String) redisOperations.opsForValue().get("key2");
                log.info("获取到key2所对应的值为{}", value);
                redisOperations.opsForValue().set("key3", "value3");
                log.info("执行exec后队列中的命令会被立刻执行");
                redisOperations.exec();
                String val = (String) redisOperations.opsForValue().get("key2");
                log.info("再次获取key2所对应的value为{}", val);
                return true;
            }
        });

        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        return map;
    }

    @PostMapping("/testsubandpub")
    public Map<String, Object> testSubAndPub(@RequestParam(value = "channel") String channel, @RequestParam(value = "message") String message){
        log.info("向{}频道发布{}消息", channel, message);
        redisTemplate.convertAndSend(channel, message);
        Map<String, Object> map = new HashMap<>(2);
        map.put("success", true);
        return map;
    }

}
