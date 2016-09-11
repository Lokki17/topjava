package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDAO {
    void createMeal(LocalDateTime dateTime, String description, int calories);
    void updateMeal(int id, LocalDateTime dateTime, String description, int calories);
    void deleteMeal(int id);
    List<Meal> getMealList();
    Meal getMeal(int id);
}
