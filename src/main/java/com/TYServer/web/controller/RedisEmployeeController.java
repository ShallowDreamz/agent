package com.TYServer.web.controller;

import com.TYServer.dto.Employee;
import com.TYServer.dto.Json;
import com.TYServer.service.JDBCService.serviceImpl.EmployeeServiceImpl;
import com.TYServer.service.jedisService.JedisService;
import com.TYServer.service.memService.MEMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class RedisEmployeeController {
    @Autowired
    private JedisService jedisService;
    @Autowired
    private MEMService MEMService;
    private final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/selectEmployeeUseRedis/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public Json selectAllEmployeeJson(@PathVariable(value = "id") String id,@RequestBody Employee employee, @RequestParam(defaultValue = "1") Integer pageIndex, String birthday) {
        logger.info("=========" + id);
        return getJson(employee, pageIndex, birthday);
    }

    private Json getJson(Employee employee, Integer pageIndex, String birthday) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("employee", employee);
        map.put("birthday", birthday);
        List list = jedisService.selectAllEmployeeUseRedis(map, pageIndex, 10);
        Json json = new Json();
        json.setData(list);
        json.setMsg("list数据测试");
        return json;
    }

    @RequestMapping(value = "/addMEM")
    @ResponseBody
    public void addMEM() {
        MEMService.addMEM();
    }

    @RequestMapping(value = "/clearMEM")
    @ResponseBody
    public void clearMEM() {
        MEMService.clearMEM();
    }
    @RequestMapping(value = "/caonimei")
    @ResponseBody
    public void caonimei(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("去你妈的大菠萝。。。。");
    }
}
