package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDAO {
    void update(Meal meal);
    void delete(int id);
    List<Meal> getList();
    Meal get(int id);
    Meal create(LocalDateTime dateTime, String description, int calories);
}
