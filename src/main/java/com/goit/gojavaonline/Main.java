package com.goit.gojavaonline;

import com.goit.gojavaonline.controllers.*;
import com.goit.gojavaonline.model.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by tamila on 8/22/16.
 */
public class Main {
    private EmployeeController employeeController;
    private DishController dishController;
    private MenuController menuController;
    private OrdersController ordersController;
    private PreparedDishController preparedDishController;
    private StorageController storageController;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = applicationContext.getBean("main", Main.class);
        main.start();
    }

    private void start() {
        getAllIngredientsFromStorage();
    }

    private void getAllIngredientsFromStorage() {
        storageController.getAll().forEach(System.out::println);
    }

    private void getIngredientFromStorage() {
        System.out.println(storageController.getIngredientFromStorage("potato"));
    }

    private void changeIngredientQuantity() {
        storageController.changeIngredientQuantity(1, 88);
    }

    private void deleteIngredientFromStorage() {
        storageController.deleteIngredientFromStorage(0);
    }

    private void insertIngredientToStorage() {
        StorageIngredient storageIngredient = new StorageIngredient();
        storageIngredient.setId(0);
        storageIngredient.setIngredientId(4);
        storageIngredient.setQuantity(100);
        storageController.insertIngredientToStorage(storageIngredient);
    }

    private void getAllPreparedDish() {
        preparedDishController.getAll().forEach(System.out::println);
    }

    private void insertPreparedDish() {
        PreparedDish preparedDish = new PreparedDish();
        preparedDish.setId(0);
        preparedDish.setDishId(0);
        preparedDish.setCookId(0);
        preparedDish.setOrderId(0);
        preparedDish.setDate("2016-1-1");
        preparedDishController.insertPreparedDish(preparedDish);
    }

    private void getOrders() {
        ordersController.getOrders(false).forEach(System.out::println);
    }

    private void closeOrder() {
        ordersController.closeOrder(0);
    }

    private void deleteOrder() {
        ordersController.deleteOrder(2);
    }

    private void deleteDishFromOrder() {
        ordersController.deleteDishFromOrder(1, 0);
    }

    private void insertDishInOrder() {
        ordersController.insertDishInOrder(2, 2);
    }

    private void insertOrder() {
        Order order = new Order();
        order.setId(2);
        order.setWaiterId(0);
        order.setTableNum(1);
        order.setOrderDate("2016-10-10");
        order.setClosed(true);
        ordersController.insertOrder(order);
    }

    private void getAllMenuDishes() {
        menuController.getAllMenuDishes(0).forEach(System.out::println);
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

    public void setOrdersController(OrdersController ordersController) {
        this.ordersController = ordersController;
    }

    public void setPreparedDishController(PreparedDishController preparedDishController) {
        this.preparedDishController = preparedDishController;
    }

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }
}
