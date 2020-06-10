package com.mattjohnson.teai7_1.controller;

import com.mattjohnson.teai7_1.dao.CarDao;
import com.mattjohnson.teai7_1.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping
public class CarController {

    private CarDao carDao;

    @Autowired
    public CarController(CarDao carDao) {
        this.carDao = carDao;
    }


    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("cars", carDao.findAll());
        model.addAttribute("newCar", new Car());

        return "app";
    }


    @PostMapping("/save")
    public String saveCar(Model model, @Valid Car car, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute("newCar", new Car());
            model.addAttribute("cars", carDao.findAll());
            model.addAttribute(errors.getFieldErrors());
            return "app";
//            return "redirect:/cars";
        }
        carDao.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars-by-year")
    public String getCarsByYears(Model model, @RequestParam(name = "minYear") int minYear,
                                 @RequestParam(name = "maxYear") int maxYear) {

        model.addAttribute("cars", carDao.filterByYear(minYear, maxYear));
        model.addAttribute("newCar", new Car());
        return "app";

    }


}
