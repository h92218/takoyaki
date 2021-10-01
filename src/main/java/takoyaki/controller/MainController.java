package takoyaki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import takoyaki.config.RedisConfig;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/redisTest")
    public ResponseEntity<?> addRedisKey() {

//        Map<String, String> waiting = new HashMap<>();
//        waiting.put("name", "현선");
//        waiting.put("phone", "01032719321");
//        waiting.put("menu", "14개");
//        waiting.put("flavor","spicy");
//
//        // Hash Operation
//        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
//        String empBobKey = "emp:Bob";
//        String empJohnKey = "emp:John";
//
//        hash.putAll("hyun", waiting);
//
//
//        System.out.println("Get waiting : " + hash.entries("hyun"));
        String key = "hyunsun";

        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();

        hash.put(key, "name", "현선");
        hash.put(key, "phone", "01032719321");
        hash.put(key, "menu", "14");

        Object hello = hash.get(key, "name");

        System.out.println("name = " + hello);

        Map<Object, Object> entries = hash.entries(key);

        System.out.println("entries = " + entries.get("phone"));

        Long size = hash.size(key);

        System.out.println("size = " + size);








        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//    @GetMapping("/redisTest/{key}")
//    public ResponseEntity<?> getRedisKey(@PathVariable String key) {
//        ValueOperations<String, String> vop = redisTemplate.opsForValue();
//        String value = vop.get(key);
//        return new ResponseEntity<>(value, HttpStatus.OK);
//    }

}
