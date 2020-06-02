package com.htp.domain;

import java.util.Objects;

public class CarModel {
    private Long id;

    private String brand;

    private String model;

    private String color;

    private Integer capacity;

    private String transmission;

    private String fuel_type;

    public CarModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return Objects.equals(id, carModel.id) &&
                Objects.equals(brand, carModel.brand) &&
                Objects.equals(model, carModel.model) &&
                Objects.equals(color, carModel.color) &&
                Objects.equals(capacity, carModel.capacity) &&
                Objects.equals(transmission, carModel.transmission) &&
                Objects.equals(fuel_type, carModel.fuel_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, color, capacity, transmission, fuel_type);
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", capacity='" + capacity + '\'' +
                ", transmission='" + transmission + '\'' +
                ", fuel_type='" + fuel_type + '\'' +
                '}';
    }
}
