package com.goit.gojavaonline;

import com.goit.gojavaonline.controllers.DishController;
import com.goit.gojavaonline.controllers.EmployeeController;
import com.goit.gojavaonline.controllers.MenuController;
import com.goit.gojavaonline.model.Dish;
import com.goit.gojavaonline.model.Employee;
import com.goit.gojavaonline.model.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by tamila on 8/22/16.
 */
public class Main {
    private EmployeeController employeeController;
    private DishController dishController;
    private MenuController menuController;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = applicationContext.getBean("main", Main.class);
        main.start();
    }

    private void start() {
        getAllMenu();
    }

    private void getAllMenu() {
        menuController.getAll().forEach(System.out::println);
    }

    private void findMenuByName(){
        System.out.println(menuController.findByName("dinner"));
    }

    private void deleteDishFromMenu() {
        menuController.deleteDishFromMenu(0, 2);
    }

    private void deleteMenu() {
        menuController.deleteMenu(2);
    }

    private void insertMenuInDish() {
        menuController.insertDishInMenu(0, 2);
    }

    private void insertMenu() {
        Menu menu = new Menu();
        menu.setId(2);
        menu.setName("new_menu");
        menuController.insertMenu(menu);
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

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
