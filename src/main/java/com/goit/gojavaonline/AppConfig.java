package com.goit.gojavaonline;

import com.goit.gojavaonline.controllers.DishController;
import com.goit.gojavaonline.controllers.EmployeeController;
import com.goit.gojavaonline.model.JdbcDishDao;
import com.goit.gojavaonline.model.JdbcEmployeeDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
    public Main main( EmployeeController employeeController, DishController dishController ){
        Main main = new Main();
        main.setEmployeeController(employeeController);
        main.setDishController(dishController);
        return main;
    }


}
