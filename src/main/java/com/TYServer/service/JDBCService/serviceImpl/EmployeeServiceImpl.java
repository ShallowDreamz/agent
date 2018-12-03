package com.TYServer.service.JDBCService.serviceImpl;

import com.TYServer.dao.EmployeeMapping;
import com.TYServer.dto.Employee;
import com.TYServer.dto.Page;
import com.TYServer.service.JDBCService.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeMapping employeeMapping;
    @Override
    public Page selectAll(Map map, Integer pageIndex,Integer rowNumber) {
        PageHelper.startPage(pageIndex,rowNumber);
        long first= System.currentTimeMillis();
        List list = employeeMapping.selectAll(map);
        long second = System.currentTimeMillis();
        //logger.info("数据库中的相应时间为：" + (second - first));
        PageInfo info = new PageInfo(list);
        Page page = new Page();
        page.setObject(list);
        page.setPageNum(pageIndex);
        long pageTotal = info.getTotal();
        if(pageTotal % rowNumber == 0){
            page.setPageCount(Integer.parseInt(pageTotal / rowNumber + ""));
        }else{
            page.setPageCount(Integer.parseInt((pageTotal / rowNumber + 1) + ""));
        }
        //Page page = getPage01(map, pageIndex,rowNumber);
        return page;
    }

/*
    public Page getPage01(Map map, Integer pageIndex,Integer rowNumber){
        Page page = getPage(map, pageIndex,rowNumber);
        return page;
    }

    public Page getPage(Map map, Integer pageIndex,Integer rowNumber){
        PageHelper.startPage(pageIndex,rowNumber);
        long first= System.currentTimeMillis();
        List list = employeeMapping.selectAll(map);
        long second = System.currentTimeMillis();
        logger.info("++++++++++++++++" + (second - first));
        PageInfo info = new PageInfo(list);
        Page page = new Page();
        page.setObject(list);
        page.setPageNum(pageIndex);
        long pageTotal = info.getTotal();
        if(pageTotal % rowNumber == 0){
            page.setPageCount(Integer.parseInt(pageTotal / rowNumber + ""));
        }else{
            page.setPageCount(Integer.parseInt((pageTotal / rowNumber + 1) + ""));
        }
        return page;
    }*/

    @Override
    public int deleteEmployeeById(Employee employee) {
        int i = employeeMapping.deleteEmployeeById(employee);
        return i;
    }

    @Override
    public Employee selectEmployeeByIdForUpdate(Employee employee) {
        Employee employee1Result = employeeMapping.selectByEmployeeById(employee);
        return employee1Result;
    }

    @Override
    public int updateEmployeeById(Employee employee) {
        int i = employeeMapping.updateEmployeeById(employee);
        return i;
    }

    @Override
    public int insertEmployee(Employee employee) {
        int i = employeeMapping.insertEmployee(employee);
        return i;
    }

    @Override
    public int createTable(long id) {
        return employeeMapping.createTable(id);
    }

    @Override
    public int dropTable(long id) {
        return employeeMapping.dropTable(id);
    }

    @Override
    public List selectResultByName() {
        List list = employeeMapping.selectResultByName();
        return list;
    }
}
