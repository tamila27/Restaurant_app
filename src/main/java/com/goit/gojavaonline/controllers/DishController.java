package com.goit.gojavaonline.controllers;

import com.goit.gojavaonline.model.Dish;
import com.goit.gojavaonline.model.dao.DishDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/23/16.
 */
public class DishController {
    private DishDao dishDao;

    @Transactional
    public void insertDish(Dish dish){
        dishDao.insertDish(dish);
    }

    @Transactional
    public void deleteDish(int id){
        dishDao.deleteDish(id);
    }

    @Transactional
    public Dish findByName(String name){
        return dishDao.findByName(name);
    }

    @Transactional
    public List<Dish> getAll(){
        return dishDao.getAll();
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
