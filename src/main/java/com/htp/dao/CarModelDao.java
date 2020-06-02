package com.htp.dao;

import com.htp.domain.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarModelDao {
    List<CarModel> findAll();

    Optional<CarModel> findById(Long carModelId);

    CarModel findOne(Long carModelId);

    void saveBatch(List<CarModel> carModels);

    CarModel save(CarModel carModel);

    CarModel update(CarModel carModel);

    int delete(CarModel carModel);

}
