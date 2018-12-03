package com.TYServer.dao;

import com.TYServer.dto.Employee;
import java.util.List;
import java.util.Map;
public interface EmployeeMapping {
    List selectAll(Map map);
    Employee selectByEmployeeById(Employee employee);
    int deleteEmployeeById(Employee employee);
    int updateEmployeeById(Employee employee);
    int insertEmployee(Employee employee);
    int createTable(long id);
    int dropTable(long id);
    List selectResultByName();
}
