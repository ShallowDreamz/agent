package com.TYServer.web.controller;

import com.TYServer.dto.Employee;
import com.TYServer.service.JDBCService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class JDBCController {
    @Autowired
    private EmployeeService employeeService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @RequestMapping(value = "/getResultSelectName",produces = "application/json;charset=UTF-8",method= RequestMethod.POST)
    @ResponseBody
    public List getResultSelectName(@RequestBody Employee employee, @RequestParam(defaultValue = "1")Integer pageIndex, String birthday){
        List list = employeeService.selectResultByName();
        return list;
    }
    @RequestMapping(value = "/insert",produces = "application/json;charset=UTF-8",method= RequestMethod.POST)
    @ResponseBody
    public int insert(@RequestBody Employee employee,String birthday){
        int i = employeeService.insertEmployee(employee);
        return i;
    }
}
