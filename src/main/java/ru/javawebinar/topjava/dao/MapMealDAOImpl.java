package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MockDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MapMealDAOImpl implements MealDAO {
    private static MockDB mockDB = MockDB.getInstance();

    private static AtomicInteger atomicCount;

    public MapMealDAOImpl() {
        if (atomicCount == null) {
            atomicCount = new AtomicInteger(0);
        }
    }

    @Override
    public void update(int id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = mockDB.getMEAL().get(id);
        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);
        mockDB.getMEAL().put(id, meal);
    }

    @Override
    public void delete(int id) {
        mockDB.getMEAL().remove(id);
        atomicCount.decrementAndGet();
        }

    @Override
    public List<Meal> getList() {
        List<Meal> result = new ArrayList<>();
        for (Integer integer : mockDB.getMEAL().keySet()) {
            result.add(mockDB.getMEAL().get(integer));
        }
        return result;
    }

    @Override
    public Meal get(int id) {
        return mockDB.getMEAL().get(id);
    }

    @Override
    public Meal create(int id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(id, dateTime, description, calories);
        atomicCount.addAndGet(1);
        return meal;
    }

    public static AtomicInteger getAtomicCount() {
        return atomicCount;
    }
}
