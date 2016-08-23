package com.goit.gojavaonline;

import com.goit.gojavaonline.controllers.DishController;
import com.goit.gojavaonline.controllers.EmployeeController;
import com.goit.gojavaonline.model.Dish;
import com.goit.gojavaonline.model.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by tamila on 8/22/16.
 */
public class Main {
    private EmployeeController employeeController;
    private DishController dishController;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = applicationContext.getBean("main", Main.class);
        main.start();
    }

    private void start() {
        //getAllEmployee();
        //insertEmployee();

        getAllDishes();
    }

    private void getAllDishes(){
        dishController.getAll().forEach(System.out::println);
    }

    private void findDishByName() {
        System.out.println(dishController.findByName("pizza"));
    }

    private void insertDish(){
        Dish dish = new Dish();
        dish.setId(2);
        dish.setName("aaa");
        dish.setCategory(0);
        dish.setPrice(55);
        dish.setWeight(90);

        dishController.insertDish(dish);
    }

    private void deleteDish(){
        dishController.deleteDish(2);
    }

    private void getAllEmployee() {
        employeeController.getAllEmployee().forEach(System.out::println);
        System.out.println(employeeController.getEmployeeByName("Ann"));
    }

    private void insertEmployee() {
        Employee employee = new Employee();
        employee.setId(2);
        employee.setLastName("aaa");
        employee.setName("ccc");
        employee.setBirthDate("1998-10-10");
        employee.setPhone("0987654");
        employee.setPosition(0);
        employee.setSalary(50000);
        employeeController.insertEmployee(employee);

        getAllEmployee();
    }

    private void deleteEmployee() {
        employeeController.deleteEmployee(2);

        getAllEmployee();
    }


    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }
}
