package takoyaki.service;

import io.netty.util.internal.StringUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.jdbc.Null;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import takoyaki.mapper.DataMapper;
import takoyaki.model.DataDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private DataMapper dataMapper;


    public DataDto selectCustomer(String phone){
        DataDto result;
        result = dataMapper.selectCustomer(phone);
        return result;
    }

    public String insertCustomer(DataDto dataDto){
        String result="";
        try {
            dataMapper.insertCustomer(dataDto);
            result = "등록 완료";
        }catch (Exception e){
            e.printStackTrace();
            result = "등록 실패";
        }
        return result;
    }
}
