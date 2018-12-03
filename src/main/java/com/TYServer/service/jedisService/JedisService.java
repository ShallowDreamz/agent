package com.TYServer.service.jedisService;


import java.util.List;
import java.util.Map;

public interface JedisService {
    List selectAllEmployeeUseRedis(Map map, Integer pageIndex, Integer rowNumber);
}
