package com.TYServer.service.jedisService.JedisServiceImpl;

import com.TYServer.jedis.JedisDao;
import com.TYServer.dto.Employee;
import com.TYServer.dto.Page;
import com.TYServer.service.JDBCService.EmployeeService;
import com.TYServer.service.jedisService.JedisService;
import com.TYServer.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JedisServiceImpl implements JedisService {
    private static final Logger logger = LoggerFactory.getLogger(JedisServiceImpl.class);
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JedisDao jedisDao;
    @Override
    public List selectAllEmployeeUseRedis(Map map, Integer pageIndex, Integer rowNumber) {
        String key = JsonUtil.MapToJson(map);
        try {
            List employee = jedisDao.lrange(key,0,0);
            if(employee != null && employee.size() != 0){
                //logger.info("redis读取的数据为：" + employee);
                return employee;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Page page = employeeService.selectAll(map,pageIndex,rowNumber);
        List<Employee> list = new ArrayList<Employee>();
        for(int i = 0;i <= page.getObject().size() - 1;i++){
            list.add((Employee) page.getObject().get(i));
        }
        if(list.size() != 0){
            for(int i = 0;i <= list.size() - 1;i++){
                this.jedisDao.lpush(key,list.get(i).toString());
            }
            this.jedisDao.expire(key, 30);
        }
        return list;
    }
}
