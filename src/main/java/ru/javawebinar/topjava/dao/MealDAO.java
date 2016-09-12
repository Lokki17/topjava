package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDAO {
    void update(int id, LocalDateTime dateTime, String description, int calories);
    void delete(int id);
    List<Meal> getList();
    Meal get(int id);
    Meal create(int id, LocalDateTime dateTime, String description, int calories);
}
