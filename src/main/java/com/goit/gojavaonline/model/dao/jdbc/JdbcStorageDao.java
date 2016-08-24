package com.goit.gojavaonline.model.dao.jdbc;

import com.goit.gojavaonline.model.Ingredient;
import com.goit.gojavaonline.model.StorageIngredient;
import com.goit.gojavaonline.model.dao.StorageDao;
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
public class JdbcStorageDao implements StorageDao {
    private static Logger LOGGER = LoggerFactory.getLogger(JdbcOrderDao.class);

    private DataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertIngredientToStorage(StorageIngredient storageIngredient) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into STORAGE values(?, ?, ? );")) {
            connection.setAutoCommit(true);
            statement.setInt(1, storageIngredient.getId());
            statement.setInt(2, storageIngredient.getIngredientId());
            statement.setFloat(3, storageIngredient.getQuantity());

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteIngredientFromStorage(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from STORAGE where ID = ?;")) {
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
    public void changeIngredientQuantity(int id, float newQuantity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("update STORAGE set QUANTITY = ? where ID = ?;")) {
            connection.setAutoCommit(true);
            statement.setFloat(1, newQuantity);
            statement.setInt(2, id);

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StorageIngredient getIngredientFromStorage(String name) {
        StorageIngredient storageIngredient;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conditionStatement = connection.prepareStatement("SELECT * FROM INGREDIENT WHERE TITLE = ?")) {
            connection.setAutoCommit(true);
            conditionStatement.setString(1, name);
            ResultSet resultSet = conditionStatement.executeQuery();
            Ingredient ingredient;
            if (resultSet.next()) {
                ingredient = getIngredient(resultSet);
                try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM STORAGE WHERE ID = ?")) {
                    statement.setInt(1, ingredient.getId());
                    resultSet = statement.executeQuery();
                    if(resultSet.next()) {
                        return getStorageIngredient(resultSet);
                    }
                    return null;
                }
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
    public List<StorageIngredient> getAll() {
        List<StorageIngredient> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM STORAGE");

            while (resultSet.next()) {
                result.add(getStorageIngredient(resultSet));

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            new RuntimeException(e);
        }
        return result;
    }

    private StorageIngredient getStorageIngredient(ResultSet resultSet) throws SQLException {
        StorageIngredient storageIngredient = new StorageIngredient();
        storageIngredient.setId(resultSet.getInt("ID"));
        storageIngredient.setIngredientId(resultSet.getInt("INGREDIENT_ID"));
        storageIngredient.setQuantity(resultSet.getInt("QUANTITY"));
        return storageIngredient;
    }

    private Ingredient getIngredient(ResultSet resultSet) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(resultSet.getInt("ID"));
        ingredient.setTitle(resultSet.getString("TITLE"));
        return ingredient;
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
