package com.example.cube.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DataSourceConfig {


//    @Bean
//    public DataSource activitiDataSource() throws ClassNotFoundException {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setUrl(url);
//        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(driverClassName));
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }

}
