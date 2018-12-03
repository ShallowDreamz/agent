package com.TYServer.jedis;

import java.util.List;

public interface JedisDao {
    String set(String key,String value);
    String get(String key);
    Long hset(String hkey,String key,String value);
    String hget(String hkey,String key);
    Long expire(String key,int second);
    Long ttl(String key);
    Long del(String key);
    Long hdel(String hkey,String key);
    Long lpush(String hkey,String value);
    List lrange(String hkey, Integer start, Integer end);
}
