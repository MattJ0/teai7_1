package com.mattjohnson.teai7_1.dao;

import com.mattjohnson.teai7_1.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(Car car) {
        String sql = "INSERT INTO cars VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getColor(), car.getYear());
    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM cars";
        List<Car> carList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> carList.add(new Car(
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                Integer.parseInt(String.valueOf(element.get("year")))
        )));

        return  carList;
    }

    @Override
    public List<Car> filterByYear(int min, int max) {
        String sql = "SELECT * FROM cars WHERE year >= ? AND year <= ?";
        List<Car> filterCarList = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, min, max);
        maps.stream().forEach(element -> filterCarList.add(new Car(
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                Integer.parseInt(String.valueOf(element.get("year")))
        )));

        return  filterCarList;
    }
}
