package com.epam.training.fooddelivery.converters;

import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.model.FoodModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FoodToFoodModelConverter implements Converter<Food,FoodModel> {

    @Override
    public FoodModel convert(Food source){
        FoodModel foodModel = new FoodModel();
        foodModel.setId(source.getId());
        foodModel.setCalorie(source.getCalorie().longValue());
        foodModel.setPrice(source.getPrice().longValue());
        foodModel.setCategory(FoodModel.CategoryEnum.valueOf(source.getCategory().toString()));
        foodModel.setDescription(source.getDescription());
        foodModel.setName(source.getName());
        return foodModel;
    }
}
