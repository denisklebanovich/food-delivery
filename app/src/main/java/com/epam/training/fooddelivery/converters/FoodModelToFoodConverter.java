package com.epam.training.fooddelivery.converters;

import com.epam.training.fooddelivery.domain.Category;
import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.model.FoodModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FoodModelToFoodConverter implements Converter<FoodModel,Food> {

    @Override
    public Food convert(FoodModel source){
        Food food = new Food();
        food.setId(source.getId());
        food.setCalorie(source.getCalorie());
        food.setPrice(source.getPrice());
        food.setCategory(Category.valueOf(source.getCategory().toString()));
        food.setDescription(source.getDescription());
        food.setName(source.getName());
        return food;
    }
}
