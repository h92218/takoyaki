package takoyaki.controller;

import org.apache.commons.collections4.MapUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/redisTest")
    public ResponseEntity<?> addRedisKey() {

        String key = "hyunsun";

        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();

        hash.put(key, "name", "현선");
        hash.put(key, "phone", "01032719321");
        hash.put(key, "menu", "3개");
        hash.put(key, "flavor", "매운맛");

        Object hello = hash.get(key, "name");
        Object hello2 = hash.get("test1", "name");

        System.out.println("name = " + hello);
        System.out.println("name = " + hello2);
        Map<Object, Object> entries = hash.entries(key);

        System.out.println("entries = " + entries.get("phone"));

        Long size = hash.size(key);

        System.out.println("size = " + size);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public void getValue(String name, String phone, JSONArray menu, JSONArray flavor){
        HashOperations<String, Object, Object> hashOp = redisTemplate.opsForHash();
        Map<Object, Object> entries = hashOp.entries(phone);

        if(MapUtils.isEmpty(entries)){
            System.out.println("entry가 null");
            setValue(name,phone,menu,flavor);
        }else{
            System.out.println("등록된 ");
            System.out.println(entries.get("name"));
            System.out.println(entries.get("menu"));
            System.out.println(entries.get("flavor"));

        }


    }

    public void setValue(String name, String phone, JSONArray menu, JSONArray flavor){
        Date myDate = new Date();
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String date = dtFormat.format(myDate);

        HashOperations<String, Object, Object> hashOp = redisTemplate.opsForHash();
        String key = phone;
        hashOp.put(key, "name", "현선");
        hashOp.put(key, "menu", menu);
        hashOp.put(key, "flavor", flavor);
        hashOp.put(key,"status","대기");
        hashOp.put(key,"등록시각",date);
    }


//    @GetMapping("/redisTest/{key}")
//    public ResponseEntity<?> getRedisKey(@PathVariable String key) {
//        ValueOperations<String, String> vop = redisTemplate.opsForValue();
//        String value = vop.get(key);
//        return new ResponseEntity<>(value, HttpStatus.OK);
//    }

    @PostMapping("/waitingRegister")
    public ResponseEntity<?> test(HttpServletRequest req)throws Exception {
        String body = readBody(req);
        System.out.println(body);

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(body);
        JSONObject jsonObj = (JSONObject) obj;
        String name = (String) jsonObj.get("name");
        String phone = (String) jsonObj.get("phone");
        JSONArray menu = (JSONArray) jsonObj.get("menu");
        JSONArray flavor = (JSONArray) jsonObj.get("flavor");

        System.out.println("name : "+ name);
        System.out.println("phone : "+ phone);
        System.out.println("menu : "+ menu);
        System.out.println("flavor : "+ flavor);

        getValue(name,phone,menu,flavor);

        return new ResponseEntity<>("성공", HttpStatus.OK);
    }

    public static String readBody(HttpServletRequest request) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(buffer);
        }
        return builder.toString();
    }


}
