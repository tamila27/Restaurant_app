package com.goit.gojavaonline.model;

import com.goit.gojavaonline.model.jdbc.MenuDao;
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
public class JdbcMenuDao implements MenuDao {
    private static Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    private DataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertMenu(Menu menu) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into MENU values(?, ? );")) {
            connection.setAutoCommit(true);
            statement.setInt(1, menu.getId());
            statement.setString(2, menu.getName());

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteMenu(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from MENU where ID = ?; ")) {
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
    public void insertDishInMenu(int menuId, int dishId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into MENU_DISH values(?, ? );")) {
            connection.setAutoCommit(true);
            statement.setInt(1, menuId);
            statement.setInt(2, dishId);

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteDishFromMenu(int menuId, int dishId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from MENU_DISH where MENU_ID = ? and DISH_ID = ?; ")) {
            connection.setAutoCommit(true);
            statement.setInt(1, menuId);
            statement.setInt(2, dishId);

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Menu findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM MENU WHERE NAME = ?")) {
            connection.setAutoCommit(true);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getMenu(resultSet);
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
    public List<Menu> getAll() {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MENU");

            while (resultSet.next()) {
                result.add(getMenu(resultSet));

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            new RuntimeException(e);
        }
        return result;
    }

    //TODO
    @Override
    public List<Dish> getAllMenuDishes(int menuId) {
        List<Dish> result = new ArrayList<>();
        /*List<Integer> dishIds = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MENU_DISH where MENU_ID = ?");

            while (resultSet.next()) {
                dishIds.add(resultSet.getInt("DISH_ID"));

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            new RuntimeException(e);
        }*/
        return result;
    }

    private Menu getMenu(ResultSet resultSet) throws SQLException {
        Menu menu = new Menu();
        menu.setId(resultSet.getInt("ID"));
        menu.setName(resultSet.getString("NAME"));
        return menu;
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
