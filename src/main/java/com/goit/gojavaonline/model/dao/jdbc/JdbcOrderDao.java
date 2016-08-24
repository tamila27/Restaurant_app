package com.goit.gojavaonline.model.dao.jdbc;

import com.goit.gojavaonline.model.Order;
import com.goit.gojavaonline.model.dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamila on 8/23/16.
 */
public class JdbcOrderDao implements OrderDao {

    private static Logger LOGGER = LoggerFactory.getLogger(JdbcOrderDao.class);

    private DataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertOrder(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into ORDERS values(?, ?, ?, ?, ? );")) {
            connection.setAutoCommit(true);
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getWaiterId());
            statement.setInt(3, order.getTableNum());
            statement.setString(4, order.getOrderDate());
            statement.setBoolean(5, order.isClosed());

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertDishInOrder(int orderId, int dishId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conditionStatement = connection.prepareStatement("select * from ORDERS where ID = ?;")) {
            connection.setAutoCommit(true);
            conditionStatement.setInt(1, orderId);
            ResultSet resultSet = conditionStatement.executeQuery();

            if(resultSet.next()){
                Order order = getOrder(resultSet);
                if( !order.isClosed() ) {
                    try (PreparedStatement statement = connection.prepareStatement("insert into ORDER_DISH values(?, ? );")) {
                        statement.setInt(1, orderId);
                        statement.setInt(2, dishId);
                        System.out.println(statement.executeUpdate());
                    }
                }
            } else {
                throw new RuntimeException("Cannot find Order with id = " + orderId);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteDishFromOrder(int orderId, int dishId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conditionStatement = connection.prepareStatement("select * from ORDERS where ID = ?;")) {

            connection.setAutoCommit(true);

            conditionStatement.setInt(1, orderId);

            ResultSet resultSet = conditionStatement.executeQuery();

            if(resultSet.next()){
                Order order = getOrder(resultSet);
                if( !order.isClosed() ) {
                    try(PreparedStatement statement = connection.prepareStatement("delete from ORDER_DISH where ORDER_ID = ? and DISH_ID = ?")) {
                        statement.setInt(1, orderId);
                        statement.setInt(2, dishId);
                        System.out.println(statement.executeUpdate());
                    }
                }
            } else {
                throw new RuntimeException("Cannot find Order with id = " + orderId);
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteOrder(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conditionStatement = connection.prepareStatement("select * from ORDERS where ID = ?;")) {
            connection.setAutoCommit(true);
            conditionStatement.setInt(1, id);
            ResultSet resultSet = conditionStatement.executeQuery();

            if(resultSet.next()){
                Order order = getOrder(resultSet);
                if(!order.isClosed()) {
                    try(PreparedStatement statement = connection.prepareStatement("delete from ORDERS where ID = ?;")) {
                        statement.setInt(1, id);

                        System.out.println(statement.executeUpdate());
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void closeOrder(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("update ORDERS set CLOSED = 1 where ID = ?;")) {
            connection.setAutoCommit(true);
            statement.setInt(1, id);

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Order> getOrders(boolean closed) {
        List<Order> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS where CLOSED = ?;")) {

            statement.setBoolean(1, closed);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(getOrder(resultSet));

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            new RuntimeException(e);
        }
        return result;
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("ID"));
        order.setWaiterId(resultSet.getInt("WAITER_ID"));
        order.setTableNum(resultSet.getInt("TABLE_NUM"));
        order.setOrderDate(resultSet.getString("ORD_DATE"));
        order.setClosed(resultSet.getBoolean("CLOSED"));
        return order;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
