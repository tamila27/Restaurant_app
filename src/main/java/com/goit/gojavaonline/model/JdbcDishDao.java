package com.goit.gojavaonline.model;

import com.goit.gojavaonline.model.jdbc.DishDao;
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
public class JdbcDishDao implements DishDao {

    private static Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    private DataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertDish(Dish dish) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into DISH values(?, ?, ?, ?, ? );")) {
            connection.setAutoCommit(true);
            statement.setInt(1, dish.getId());
            statement.setString(2, dish.getName());
            statement.setInt(3, dish.getCategory());
            statement.setFloat(4, dish.getPrice());
            statement.setFloat(5, dish.getWeight());

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteDish(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from DISH where ID = ?; ")) {
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
    public Dish findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DISH WHERE NAME = ?")) {
            connection.setAutoCommit(true);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getDish(resultSet);
            } else {
                throw new RuntimeException("Cannot find Employee with id = " + name);

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Dish> getAll() {
        List<Dish> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DISH");

            while (resultSet.next()) {
                result.add(getDish(resultSet));

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            new RuntimeException(e);
        }
        return result;
    }

    private Dish getDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("ID"));
        dish.setName(resultSet.getString("NAME"));
        dish.setCategory(resultSet.getInt("CATEGORY"));
        dish.setPrice(resultSet.getFloat("PRICE"));
        dish.setWeight(resultSet.getFloat("WEIGHT"));
        return dish;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
