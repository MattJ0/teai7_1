package com.mattjohnson.teai7_1.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Car {

  @NotBlank(message = "This field is required")
  private String mark;

  @NotBlank(message = "This field is required")
  private String model;

  @NotBlank(message = "This field is required")
  private String color;

  @Min(value = 1885, message = "The first car was produced in 1885!")
  @Max(value = Integer.MAX_VALUE, message = "Year is too high!")
  private int year;

  public Car() {
  }

  public Car(String mark, String model, String color, int year) {
    this.mark = mark;
    this.model = model;
    this.color = color;
    this.year = year;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }


  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

}
