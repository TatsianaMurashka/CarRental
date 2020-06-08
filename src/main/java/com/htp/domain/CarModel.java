package com.htp.domain;

import java.util.Objects;

public class CarModel {
    private Long id;

    private String brand;

    private String model;

    private String color;

    private Integer capacity;

    private String transmission;

    private String fuelType;

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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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
                Objects.equals(fuelType, carModel.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, color, capacity, transmission, fuelType);
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
                ", fuel_type='" + fuelType + '\'' +
                '}';
    }
}
