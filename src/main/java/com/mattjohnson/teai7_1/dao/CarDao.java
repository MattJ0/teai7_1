package com.mattjohnson.teai7_1.dao;

import com.mattjohnson.teai7_1.model.Car;

import java.util.List;

public interface CarDao {

    void saveCar(Car car);

    List<Car> findAll();

    List<Car> filterByYear(int min, int max);
}
