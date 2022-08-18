package com.epam.training.fooddelivery.controller;

import com.epam.training.fooddelivery.api.FoodserviceApi;
import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.model.FoodModel;
import com.epam.training.fooddelivery.service.FoodService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FoodController implements FoodserviceApi {

    final FoodService foodService;

    final Converter<Food,FoodModel> converter;

    public FoodController(FoodService foodService, Converter<Food, FoodModel> converter) {
        this.foodService = foodService;
        this.converter = converter;
    }

    @Override
    @GetMapping("/foodservice/foods")
    public ResponseEntity<List<FoodModel>> listAllFoods() {
        List<FoodModel> foodModels = foodService.listAllFood().stream().map(food -> converter.convert(food)).collect(Collectors.toList());
        return new ResponseEntity<>(foodModels, HttpStatus.OK);
    }
}
