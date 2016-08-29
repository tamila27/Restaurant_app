package com.goit.gojavaonline;

import com.goit.gojavaonline.controllers.*;
import com.goit.gojavaonline.model.dao.jdbc.*;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by tamila on 8/22/16.
 */
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    public Properties loadProperties(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "jdbc.properties";
            input = AppConfig.class.getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    @Bean
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Properties properties = loadProperties();
        if(properties != null){
            try {
                dataSource.setDriverClass(properties.getProperty("jdbc.driver.class"));
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
            dataSource.setUser(properties.getProperty("jdbc.user"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            dataSource.setMinPoolSize(Integer.valueOf(properties.getProperty("jdbc.min.connections")));
            dataSource.setMaxPoolSize(Integer.valueOf(properties.getProperty("jdbc.max.connections")));
            dataSource.setAcquireIncrement(Integer.valueOf(properties.getProperty("jdbc.acquire.increment")));
        }
        return dataSource;
    }

    @Bean
    public JdbcEmployeeDao employeeDao(ComboPooledDataSource dataSource ) {
        JdbcEmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();
        jdbcEmployeeDao.setDataSource(dataSource);
        return jdbcEmployeeDao;
    }

    @Bean
    public EmployeeController employeeController(JdbcEmployeeDao employeeDao) {
        EmployeeController employeeController = new EmployeeController();
        employeeController.setEmployeeDao(employeeDao);
        return employeeController;

    }

    @Bean
    public JdbcDishDao dishDao(ComboPooledDataSource dataSource ) {
        JdbcDishDao jdbcDishDao = new JdbcDishDao();
        jdbcDishDao.setDataSource(dataSource);
        return jdbcDishDao;
    }

    @Bean
    public DishController dishController(JdbcDishDao dishDao) {
        DishController dishController = new DishController();
        dishController.setDishDao(dishDao);
        return dishController;

    }

    @Bean
    public JdbcMenuDao menuDao(ComboPooledDataSource dataSource ) {
        JdbcMenuDao jdbcMenuDao = new JdbcMenuDao();
        jdbcMenuDao.setDataSource(dataSource);
        return jdbcMenuDao;
    }

    @Bean
    public MenuController menuController(JdbcMenuDao menuDao) {
        MenuController menuController = new MenuController();
        menuController.setMenuDao(menuDao);
        return menuController;

    }

    @Bean
    public JdbcOrderDao orderDao(ComboPooledDataSource dataSource ) {
        JdbcOrderDao jdbcOrderDao = new JdbcOrderDao();
        jdbcOrderDao.setDataSource(dataSource);
        return jdbcOrderDao;
    }

    @Bean
    public OrdersController orderController(JdbcOrderDao orderDao) {
        OrdersController orderController = new OrdersController();
        orderController.setOrderDao(orderDao);
        return orderController;

    }

    @Bean
    public JdbcPreparedDishDao preparedDishDao(ComboPooledDataSource dataSource ) {
        JdbcPreparedDishDao jdbcPreparedDishDao = new JdbcPreparedDishDao();
        jdbcPreparedDishDao.setDataSource(dataSource);
        return jdbcPreparedDishDao;
    }

    @Bean
    public PreparedDishController preparedDishController(JdbcPreparedDishDao preparedDishDao) {
        PreparedDishController preparedDishController = new PreparedDishController();
        preparedDishController.setPreparedDishDao(preparedDishDao);
        return preparedDishController;

    }

    @Bean
    public JdbcStorageDao storageDao(ComboPooledDataSource dataSource ) {
        JdbcStorageDao jdbcStorageDao = new JdbcStorageDao();
        jdbcStorageDao.setDataSource(dataSource);
        return jdbcStorageDao;
    }

    @Bean
    public StorageController StorageController(JdbcStorageDao storageDao) {
        StorageController storageController = new StorageController();
        storageController.setStorageDao(storageDao);
        return storageController;

    }

    @Bean
    public Main main(EmployeeController employeeController, DishController dishController,
                     MenuController menuController, OrdersController ordersController,
                     PreparedDishController preparedDishController, StorageController storageController){
        Main main = new Main();
        main.setEmployeeController(employeeController);
        main.setDishController(dishController);
        main.setMenuController(menuController);
        main.setOrdersController(ordersController);
        main.setPreparedDishController(preparedDishController);
        main.setStorageController(storageController);
        return main;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(ComboPooledDataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.goit.gojavaonline.model");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }

}
