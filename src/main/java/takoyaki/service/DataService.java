package takoyaki.service;

import org.apache.commons.collections4.MapUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import takoyaki.mapper.DataMapper;
import takoyaki.model.DataDto;

import java.util.Map;

@Service
public class DataService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private DataMapper dataMapper;


    public DataDto selectCustomer(DataDto paramDto){
        DataDto result;
        result = dataMapper.selectCustomer(paramDto);
        return result;
    }
}
