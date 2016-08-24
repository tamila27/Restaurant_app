package com.goit.gojavaonline.controllers;

import com.goit.gojavaonline.model.Dish;
import com.goit.gojavaonline.model.Menu;
import com.goit.gojavaonline.model.dao.MenuDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/23/16.
 */
public class MenuController {
    private MenuDao menuDao;

    @Transactional
    public void insertMenu(Menu menu) {
        menuDao.insertMenu(menu);
    }

    @Transactional
    public void deleteMenu(int id) {
        menuDao.deleteMenu(id);
    }

    @Transactional
    public void insertDishInMenu(int menuId, int dishId) {
        menuDao.insertDishInMenu(menuId, dishId);
    }

    @Transactional
    public void deleteDishFromMenu(int menuId, int dishId) {
        menuDao.deleteDishFromMenu(menuId, dishId);
    }

    @Transactional
    public Menu findByName(String name) {
        return menuDao.findByName(name);
    }

    @Transactional
    public List<Menu> getAll() {
        return menuDao.getAll();
    }

    public List<Dish> getAllMenuDishes(int menuId) {
        return menuDao.getAllMenuDishes(menuId);
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
}
