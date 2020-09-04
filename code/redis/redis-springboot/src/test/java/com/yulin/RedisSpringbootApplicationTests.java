package com.yulin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulin.pojo.User;
import com.yulin.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 这里使用RedisTemplate的默认API来进行操作
     */
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("key1", "value1");
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

    @Test
    void test() throws JsonProcessingException {
        User user = new User("yulin2", 22);
        redisTemplate.opsForValue().set("yulin2",user);
        System.out.println(redisTemplate.opsForValue().get("yulin2"));
    }

    @Test
    void testUtils(){
        redisUtils.set("redisutils",new User("redisutils",23));
        System.out.println(redisUtils.get("redisutils"));
    }

}
//User(name=yulin1, age=20)
//User(name=yulin2, age=22)