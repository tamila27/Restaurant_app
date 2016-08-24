package com.goit.gojavaonline.model.dao;

import com.goit.gojavaonline.model.Order;

import java.util.List;

/**
 * Created by tamila on 8/23/16.
 */
public interface OrderDao {
    void insertOrder(Order order);
    void insertDishInOrder(int orderId, int dishId);
    void deleteDishFromOrder(int orderId, int dishId);
    void deleteOrder(int id);
    void closeOrder(int id);
    List<Order> getOrders(boolean closed);

}
