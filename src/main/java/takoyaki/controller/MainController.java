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
import takoyaki.model.DataDto;
import takoyaki.service.DataService;

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
    DataService dataService;

    @PostMapping("/registerWaiting")
    public ResponseEntity<?> test(HttpServletRequest req)throws Exception {
        String body = readBody(req);

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

        DataDto dataDto  = new DataDto();
        dataDto.setPhone(phone);

        DataDto result;
        result = dataService.selectCustomer(dataDto);

        System.out.println("select 결과 : " + result.getName());




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
