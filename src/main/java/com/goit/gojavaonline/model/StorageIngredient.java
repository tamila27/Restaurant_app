package com.goit.gojavaonline.model;

/**
 * Created by tamila on 8/24/16.
 */
public class StorageIngredient {
    private int id;
    private int ingredientId;
    private float quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StorageIngredient{" +
                "id=" + id +
                ", ingredientId=" + ingredientId +
                ", quantity=" + quantity +
                '}';
    }
}
