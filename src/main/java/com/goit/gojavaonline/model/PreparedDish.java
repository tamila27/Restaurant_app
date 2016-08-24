package com.goit.gojavaonline.model;

/**
 * Created by tamila on 8/24/16.
 */
public class PreparedDish {
    private int id;
    private int dishId;
    private int cookId;
    private int orderId;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getCookId() {
        return cookId;
    }

    public void setCookId(int cookId) {
        this.cookId = cookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PreparedDish{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", cookId=" + cookId +
                ", orderId=" + orderId +
                ", date='" + date + '\'' +
                '}';
    }
}
