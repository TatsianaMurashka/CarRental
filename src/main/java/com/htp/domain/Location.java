package com.htp.domain;

import java.util.Objects;

public class Location {
    private Long id;

    private String country;

    private String city;

    private String street;

    private String house;

    private String apartment;

    public Location() {
    }

    public Location(String country, String city, String street, String house, String apartment) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) &&
                Objects.equals(country, location.country) &&
                Objects.equals(city, location.city) &&
                Objects.equals(street, location.street) &&
                Objects.equals(house, location.house) &&
                Objects.equals(apartment, location.apartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, house, apartment);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
