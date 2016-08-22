package com.goit.gojavaonline.controllers;

import com.goit.gojavaonline.model.Employee;
import com.goit.gojavaonline.model.jdbc.EmployeeDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/22/16.
 */
public class EmployeeController {
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployee() {
        return employeeDao.getAll();
    }

    @Transactional
    public Employee getEmployeeByName(String name) {
        return employeeDao.loadByName(name);
    }

    @Transactional
    public void insertEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployeeById(id);
    }


    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
