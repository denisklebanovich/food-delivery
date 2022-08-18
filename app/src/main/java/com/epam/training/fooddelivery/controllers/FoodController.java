package com.epam.training.fooddelivery.controllers;

import com.epam.training.fooddelivery.api.FoodserviceApi;
import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.model.FoodModel;
import com.epam.training.fooddelivery.service.FoodService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FoodController implements FoodserviceApi {

    final FoodService foodService;

    final Converter<Food,FoodModel> converter;

    public FoodController(FoodService foodService, Converter<Food, FoodModel> converter) {
        this.foodService = foodService;
        this.converter = converter;
    }

    @Override
    public ResponseEntity<List<FoodModel>> listAllFoods() {
        List<FoodModel> foodModels = new ArrayList<>();
        foodService.listAllFood().forEach(food -> foodModels.add(converter.convert(food)));
        return new ResponseEntity<>(foodModels, HttpStatus.OK);
    }
}
