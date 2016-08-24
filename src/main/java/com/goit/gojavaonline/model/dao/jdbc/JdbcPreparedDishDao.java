package com.goit.gojavaonline.model.dao.jdbc;

import com.goit.gojavaonline.model.PreparedDish;
import com.goit.gojavaonline.model.dao.PreparedDishDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamila on 8/24/16.
 */
public class JdbcPreparedDishDao implements PreparedDishDao {

    private static Logger LOGGER = LoggerFactory.getLogger(JdbcOrderDao.class);

    private DataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertPreparedDish(PreparedDish preparedDish) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into PREPARED_DISH values(?, ?, ?, ?, ? );")) {
            connection.setAutoCommit(true);
            statement.setInt(1, preparedDish.getId());
            statement.setInt(2, preparedDish.getDishId());
            statement.setInt(3, preparedDish.getCookId());
            statement.setInt(4, preparedDish.getOrderId());
            statement.setString(5, preparedDish.getDate());

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<PreparedDish> getAll() {
        List<PreparedDish> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PREPARED_DISH");

            while (resultSet.next()) {
                result.add(getPreparedDish(resultSet));

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            new RuntimeException(e);
        }
        return result;
    }

    private PreparedDish getPreparedDish(ResultSet resultSet) throws SQLException {
        PreparedDish preparedDish = new PreparedDish();
        preparedDish.setId(resultSet.getInt("ID"));
        preparedDish.setDishId(resultSet.getInt("DISH_ID"));
        preparedDish.setCookId(resultSet.getInt("COOK_ID"));
        preparedDish.setOrderId(resultSet.getInt("COOK_ID"));
        preparedDish.setDate(resultSet.getString("DATE"));
        return preparedDish;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
