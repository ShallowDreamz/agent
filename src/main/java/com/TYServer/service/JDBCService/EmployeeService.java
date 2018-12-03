package com.TYServer.service.JDBCService;

import com.TYServer.dto.Employee;
import com.TYServer.dto.Page;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Page selectAll(Map map,Integer pageIndex,Integer rowNumber);
    int deleteEmployeeById(Employee employee);
    Employee selectEmployeeByIdForUpdate(Employee employee);
    int updateEmployeeById(Employee employee);
    int insertEmployee(Employee employee);
    int createTable(long id);
    int dropTable(long id);
    List selectResultByName();
}
