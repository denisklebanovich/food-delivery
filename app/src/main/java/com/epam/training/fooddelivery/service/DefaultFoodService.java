package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.repository.FoodRepository;
import com.epam.training.fooddelivery.domain.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultFoodService implements FoodService{
    private final FoodRepository foodRepository;


    public  DefaultFoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> listAllFood() {
        return foodRepository.findAll();
    }
}
