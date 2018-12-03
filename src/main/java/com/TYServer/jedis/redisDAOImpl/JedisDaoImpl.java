package com.TYServer.jedis.redisDAOImpl;

import com.TYServer.jedis.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Component
public class JedisDaoImpl implements JedisDao {

    @Autowired
    private JedisPool jedisPool;

    public JedisDaoImpl()
    {
        System.out.println("xxxxxxxxxxxx");
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.set(key, value);
        //jedis.close();
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get(key);
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long str = jedis.hset(hkey, key, value);
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.hget(hkey, key);
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public Long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        long str = jedis.expire(key, second);
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        long str = jedis.ttl(key);
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        long str = jedis.del(key);
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public Long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        long str = jedis.hdel(hkey, key);
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public Long lpush(String hkey, String value) {
        Jedis jedis = jedisPool.getResource();
        long str = jedis.lpush(hkey, value);
        jedisPool.returnResource(jedis);
        return str;
    }

    @Override
    public List lrange(String hkey, Integer start, Integer end) {
        Jedis jedis = jedisPool.getResource();
        List<String> list = jedis.lrange(hkey,start,end);
        jedisPool.returnResource(jedis);
        return list;
    }
}
