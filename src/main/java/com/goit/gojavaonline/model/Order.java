package com.goit.gojavaonline.model;

/**
 * Created by tamila on 8/23/16.
 */
public class Order {
    private int id;
    private int waiterId;
    private int tableNum;
    private String orderDate;
    private boolean closed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", waiterId=" + waiterId +
                ", tableNum=" + tableNum +
                ", orderDate='" + orderDate + '\'' +
                ", closed=" + closed +
                '}';
    }
}
