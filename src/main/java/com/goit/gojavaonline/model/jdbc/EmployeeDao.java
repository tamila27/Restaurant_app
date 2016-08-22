package com.goit.gojavaonline.model.jdbc;

import com.goit.gojavaonline.model.Employee;

import java.util.List;

/**
 * Created by tamila on 8/22/16.
 */
public interface EmployeeDao {
    List<Employee> getAll();
    Employee loadById(int id);
    Employee loadByName(String name);
    void insertEmployee(Employee employee);
    void deleteEmployeeById(int id);
}
