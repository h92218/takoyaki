package takoyaki.controller;

import oracle.jdbc.proxy.annotation.Post;
import org.apache.commons.collections4.MapUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import takoyaki.config.RedisConfig;
import takoyaki.model.DataDto;
import takoyaki.service.DataService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    DataService dataService;

    @PostMapping("/checkRegister")
    public ResponseEntity<?> checkRegister(HttpServletRequest req)throws Exception{
        String body = readBody(req);

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(body);
        JSONObject jsonObj = (JSONObject) obj;
        String phone = (String) jsonObj.get("phone");

        DataDto resultDto = dataService.selectCustomer(phone);

        if(resultDto == null){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }else{
            String regidate = resultDto.getRegisterDate();
            Date myDate = new Date();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date regiTime   = dtFormat.parse(regidate);
            long diffMinute = ((myDate.getTime() - regiTime.getTime())/1000/60);
            int newTime = (int)resultDto.getRemainTime() - (int)diffMinute;

            if(newTime<0){
                newTime=0;
            }
            resultDto.setRemainTime(newTime);

            return new ResponseEntity<>(resultDto, HttpStatus.OK);
        }

    }

    @PostMapping("/registerWaiting")
    public ResponseEntity<?> registerWaiting(HttpServletRequest req)throws Exception {
        String body = readBody(req);

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(body);
        JSONObject jsonObj = (JSONObject) obj;
        String phone = (String) jsonObj.get("phone");
        int remainTime = Integer.valueOf((String)jsonObj.get("remainTime"));
        JSONArray menu = (JSONArray) jsonObj.get("menu");
        JSONArray flavor = (JSONArray) jsonObj.get("flavor");


        DataDto resultDto = dataService.selectCustomer(phone);

        String message="";
        DataDto paramDto = new DataDto();

        if(resultDto == null){
            paramDto.setPhone(phone);
            paramDto.setRemainTime(remainTime);
            String mmenu="";
            for(int i=0; i< menu.size();i++){
                mmenu+= " " + menu.get(i);
            }
            paramDto.setMenu(mmenu);
            String fflavor="";
            for(int i=0; i< flavor.size();i++){
                fflavor+= " "+ flavor.get(i);
            }
            paramDto.setFlavor(fflavor);

            message = dataService.insertCustomer(paramDto);
        }else{
            message = "이미 등록된 번호입니다.";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("deleteCustomer")
    public ResponseEntity<?> deleteCustomer(String phone){
        System.out.println("deleteCustomer");
        String result = dataService.deleteCustomer(phone);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("changeMenu")
    public ResponseEntity<?> changeMenu(HttpServletRequest req) throws Exception{
        String body = readBody(req);

        org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(body);
        JSONObject jsonObj = (JSONObject) obj;
        String phone = (String) jsonObj.get("phone");
        JSONArray menu = (JSONArray) jsonObj.get("menu");
        JSONArray flavor = (JSONArray) jsonObj.get("flavor");

        DataDto paramDto = new DataDto();
        paramDto.setPhone(phone);

        String mmenu="";
        for(int i=0; i< menu.size();i++) {
            mmenu += " " + menu.get(i);
        }
        paramDto.setMenu(mmenu);

        String fflavor="";
        for(int i=0; i< flavor.size();i++){
            fflavor+= " "+ flavor.get(i);
        }
        paramDto.setFlavor(fflavor);
        String message = dataService.changeMenu(paramDto);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/selectCount")
    @ResponseBody
    public int selectCount(){

        return dataService.selectCount();
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

    @GetMapping("/selectAll")
    @ResponseBody
    public ResponseEntity<?> selectAll(){
        List<DataDto> result = dataService.selectAll();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/getTime")
    @ResponseBody
    public String getTime(){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String time =  valueOperations.get("time");
        return time;
    }

    @GetMapping("/selectWaitingCount")
    @ResponseBody
    public int selectWaitingCount(String phone){
        return dataService.selectWaitingCount(phone);
    }

    @GetMapping("/setTime")
    @ResponseBody
    public String setTime(double val){
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.increment("time",val);
        if(Double.valueOf(getTime())<=0){
            valueOp.set("time","0");
        }
        return getTime();

    }

    @GetMapping("completeCustomer")
    @ResponseBody
    public void completeCustomer(String phone){
        dataService.completeCustomer(phone);
    }



    }
