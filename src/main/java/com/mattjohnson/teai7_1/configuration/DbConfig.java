package com.mattjohnson.teai7_1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void createTable() {
//        String sql = "CREATE TABLE cars(mark varchar(255), model varchar(255), color varchar(255), year int)";
//        getJdbcTemplate().update(sql);
//    }

    @EventListener(ApplicationReadyEvent.class)
    @Order(0)
    public void deleteData() {
        String sql = "DELETE from cars";
        getJdbcTemplate().update(sql);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Order(1)
    public void loadData() {
        String sql = "INSERT INTO cars (mark, model, color, year) VALUES ('fiat', 'panda', 'red', 2015), ('fiat', 'panda', 'red', 2000), ('fiat', 'panda', 'red', 2005), ('fiat', 'panda', 'red', 2010) ";
        getJdbcTemplate().update(sql);
    }


}
