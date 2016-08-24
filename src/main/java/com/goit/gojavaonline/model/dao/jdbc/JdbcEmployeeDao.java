package com.goit.gojavaonline.model.dao.jdbc;

import com.goit.gojavaonline.model.Employee;
import com.goit.gojavaonline.model.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamila on 8/22/16.
 */
public class JdbcEmployeeDao implements EmployeeDao{

    private static Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDao.class);

    private DataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");

            while (resultSet.next()) {
                result.add(getEmployee(resultSet));

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Employee loadById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE ID = ?")) {
            connection.setAutoCommit(true);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getEmployee(resultSet);
            } else {
                throw new RuntimeException("Cannot find Employee with id = " + id);

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Employee loadByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE NAME = ?")) {
            connection.setAutoCommit(true);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getEmployee(resultSet);
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
    public void insertEmployee(Employee employee) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into EMPLOYEE values(?, ?, ?, ?, ?, ?, ? );")) {
            connection.setAutoCommit(true);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getName());
            statement.setString(4, employee.getBirthDate());
            statement.setString(5, employee.getPhone());
            statement.setInt(6, employee.getPosition());
            statement.setFloat(7, employee.getSalary());

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteEmployeeById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from EMPLOYEE where ID = ?; ")) {
            connection.setAutoCommit(true);
            statement.setInt(1, id);

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB ", e);
            throw new RuntimeException(e);
        }
    }

    private Employee getEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("ID"));
        employee.setLastName(resultSet.getString("LAST_NAME"));
        employee.setName(resultSet.getString("NAME"));
        employee.setBirthDate(resultSet.getString("BIRTH_DATE"));
        employee.setPhone(resultSet.getString("PHONE"));
        employee.setPosition(resultSet.getInt("POSITION"));
        employee.setSalary(resultSet.getFloat("SALARY"));
        return employee;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
