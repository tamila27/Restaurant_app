package com.goit.gojavaonline.controllers;

import com.goit.gojavaonline.model.PreparedDish;
import com.goit.gojavaonline.model.dao.PreparedDishDao;

import java.util.List;

/**
 * Created by tamila on 8/24/16.
 */
public class PreparedDishController {
    private PreparedDishDao preparedDishDao;

    public void insertPreparedDish(PreparedDish preparedDish){
        preparedDishDao.insertPreparedDish(preparedDish);
    }
    public List<PreparedDish> getAll() {
        return preparedDishDao.getAll();
    }


    public void setPreparedDishDao(PreparedDishDao preparedDishDao) {
        this.preparedDishDao = preparedDishDao;
    }
}
