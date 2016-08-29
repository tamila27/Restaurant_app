package com.goit.gojavaonline.model.dao;

import com.goit.gojavaonline.model.StorageIngredient;

import java.util.List;

/**
 * Created by tamila on 8/24/16.
 */
public interface StorageDao {
    void insertIngredientToStorage(StorageIngredient storageIngredient);
    void deleteIngredientFromStorage(int id);
    void changeIngredientQuantity(int id, float newQuantity);
    StorageIngredient getIngredientFromStorage(String name);
    List<StorageIngredient> getAll();

}
