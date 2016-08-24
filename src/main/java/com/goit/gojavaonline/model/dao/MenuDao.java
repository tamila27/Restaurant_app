package com.goit.gojavaonline.model.dao;

import com.goit.gojavaonline.model.Dish;
import com.goit.gojavaonline.model.Menu;

import java.util.List;

/**
 * Created by tamila on 8/23/16.
 */
public interface MenuDao {
    void insertMenu(Menu menu);
    void deleteMenu(int id);
    void insertDishInMenu(int menuId, int dishId);
    void deleteDishFromMenu(int menuId, int dishId);
    Menu findByName(String name);
    List<Menu> getAll();
    List<Dish> getAllMenuDishes(int menuId);
}
