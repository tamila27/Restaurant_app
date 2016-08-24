package com.goit.gojavaonline.controllers;

import com.goit.gojavaonline.model.StorageIngredient;
import com.goit.gojavaonline.model.dao.StorageDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/24/16.
 */
public class StorageController {
    private StorageDao storageDao;

    @Transactional
    public void insertIngredientToStorage(StorageIngredient storageIngredient) {
        storageDao.insertIngredientToStorage(storageIngredient);
    }

    @Transactional
    public void deleteIngredientFromStorage(int id) {
        storageDao.deleteIngredientFromStorage(id);
    }

    @Transactional
    public void changeIngredientQuantity(int id, float newQuantity) {
        storageDao.changeIngredientQuantity(id, newQuantity);
    }

    @Transactional
    public StorageIngredient getIngredientFromStorage(String name) {
        return storageDao.getIngredientFromStorage(name);
    }

    @Transactional
    public List<StorageIngredient> getAll() {
        return storageDao.getAll();
    }

    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }
}
