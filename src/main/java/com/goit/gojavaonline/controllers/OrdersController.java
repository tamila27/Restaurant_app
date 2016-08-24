package com.goit.gojavaonline.controllers;

import com.goit.gojavaonline.model.Order;
import com.goit.gojavaonline.model.dao.OrderDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/23/16.
 */
public class OrdersController {
    private OrderDao orderDao;

    @Transactional
    public void insertOrder(Order order){
        orderDao.insertOrder(order);
    }

    @Transactional
    public void insertDishInOrder(int orderId, int dishId){
        orderDao.insertDishInOrder(orderId, dishId);
    }

    @Transactional
    public void deleteDishFromOrder(int orderId, int dishId) {
        orderDao.deleteDishFromOrder(orderId, dishId);
    }

    @Transactional
    public void deleteOrder(int id) {
        orderDao.deleteOrder(id);
    }

    @Transactional
    public void closeOrder(int id) {
        orderDao.closeOrder(id);
    }

    @Transactional
    public List<Order> getOrders(boolean closed) {
        return orderDao.getOrders(closed);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
