package com.example.demo;

/***
 * @author
 */

import org.aspectj.apache.bcel.Repository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
// 驱动spring缓存机制
@EnableCaching
//@ComponentScan(value = {"com.example.demo.mapper"})
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class RedisApplication {


    /***
     *
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private MessageListener messageListener;
    private ThreadPoolTaskScheduler threadPoolTaskScheduler = null;
    private RedisMessageListenerContainer redisMessageListenerContainer = null;

    @PostConstruct
    public void init() {
        initRedisTempiate();
    }

    /***
     * 序列化键值对
     */
    private void initRedisTempiate() {
        RedisSerializer redisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        //redisTemplate.setHashValueSerializer(redisSerializer);
    }

    /***
     * 创建redis连接池，运行等待处理的redis消息
     * @return
     */
   /* @Bean
    public ThreadPoolTaskScheduler initThreadPool(){
        if (threadPoolTaskScheduler != null) {
            return threadPoolTaskScheduler;
        }
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        return threadPoolTaskScheduler;
    }*/

    /***
     * 创建redis的消息监听器
     * @return
     */
/*    @Bean
    public RedisMessageListenerContainer initMessageContainer(){
        redisMessageListenerContainer = new RedisMessageListenerContainer();
        // redis连接工厂
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        // 设置运行任务池
        redisMessageListenerContainer.setTaskExecutor(initThreadPool());
        // 设置channel，订阅频道
        Topic channel = new ChannelTopic("channel1");
        Topic channel2 = new ChannelTopic("channel2");
        // 使用监听器监听redis消息队列
        redisMessageListenerContainer.addMessageListener(messageListener, channel);
        redisMessageListenerContainer.addMessageListener(messageListener, channel2);
        return redisMessageListenerContainer;
    }*/

    /*@Bean(name = "redisCacheManager")
    public RedisCacheManager initRedisCacheManager(){
        // Redis加锁写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);
        // 启动redis缓存的默认设置
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        // 设置JDK序列化器
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        // 禁用前缀
        configuration = configuration.disableKeyPrefix();
        // 设置超时时间
       // configuration = configuration.entryTtl(Duration.ofMinutes(10));
        // 创建redis缓存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(writer, configuration);
        return redisCacheManager;
    }*/
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

}
