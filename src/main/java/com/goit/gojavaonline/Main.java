package com.goit.gojavaonline;

import com.goit.gojavaonline.controllers.EmployeeController;
import com.goit.gojavaonline.model.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by tamila on 8/22/16.
 */
public class Main {
    private EmployeeController employeeController;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = applicationContext.getBean("main", Main.class);
        main.start();
    }

    private void start() {
        //employeeController.getAllEmployee().forEach(System.out::println);
        //System.out.println(employeeController.getEmployeeByName("Ann"));
        Employee employee = new Employee();
        employee.setId(2);
        employee.setLastName("aaa");
        employee.setName("ccc");
        employee.setBirthDate("1998-10-10");
        employee.setPhone("0987654");
        employee.setPosition(0);
        employee.setSalary(50000);
        employeeController.insertEmployee(employee);

        employeeController.getAllEmployee().forEach(System.out::println);

        employeeController.deleteEmployee(2);

        employeeController.getAllEmployee().forEach(System.out::println);
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }
}
