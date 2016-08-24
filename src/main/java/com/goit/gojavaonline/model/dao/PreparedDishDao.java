package com.goit.gojavaonline.model.dao;

import com.goit.gojavaonline.model.PreparedDish;

import java.util.List;

/**
 * Created by tamila on 8/24/16.
 */
public interface PreparedDishDao {
    void insertPreparedDish(PreparedDish preparedDish);
    List<PreparedDish> getAll();
}
