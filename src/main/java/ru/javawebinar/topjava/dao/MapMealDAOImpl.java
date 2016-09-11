package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MockDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MapMealDAOImpl implements MealDAO {
    private static MockDB mockDB = MockDB.getInstance();

    @Override
    public void createMeal(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories);
        mockDB.getMEAL().put(meal.getId(), meal);
    }

    @Override
    public void updateMeal(int id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = mockDB.getMEAL().get(id);
        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);
        mockDB.getMEAL().put(id, meal);
    }

    @Override
    public void deleteMeal(int id) {
        mockDB.getMEAL().remove(id);
    }

    @Override
    public List<Meal> getMealList() {
        List<Meal> result = new ArrayList<>();
        for (Integer integer : mockDB.getMEAL().keySet()){
            result.add(mockDB.getMEAL().get(integer));
        }
        return result;
    }

    @Override
    public Meal getMeal(int id) {
        return mockDB.getMEAL().get(id);
    }
}
