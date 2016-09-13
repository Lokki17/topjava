package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class MapMealDAOImpl implements MealDAO {

    private static final Map<Integer, Meal> MEAL = new ConcurrentHashMap<>();

    private static AtomicInteger atomicCount;

    public MapMealDAOImpl() {
        if (atomicCount == null) {
            atomicCount = new AtomicInteger(0);
            MEAL.put(atomicCount.get(), create(atomicCount.get(), LocalDateTime.of(2016, Month.MAY, 30, 13, 0), "Обед", 1000));
            MEAL.put(atomicCount.get(), create(atomicCount.get(), LocalDateTime.of(2016, Month.MAY, 30, 20, 0), "Ужин", 500));
            MEAL.put(atomicCount.get(), create(atomicCount.get(), LocalDateTime.of(2016, Month.MAY, 31, 10, 0), "Завтрак", 1000));
            MEAL.put(atomicCount.get(), create(atomicCount.get(), LocalDateTime.of(2016, Month.MAY, 31, 13, 0), "Обед", 500));
            MEAL.put(atomicCount.get(), create(atomicCount.get(), LocalDateTime.of(2016, Month.MAY, 31, 20, 0), "Ужин", 510));
        }
    }

    @Override
    public void update(int id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = MEAL.get(id);
        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);
        MEAL.put(id, meal);
    }

    @Override
    public void delete(int id) {
        MEAL.remove(id);
        }

    @Override
    public List<Meal> getList() {
        List<Meal> result = new ArrayList<>();
        for (Integer integer : MEAL.keySet()) {
            result.add(MEAL.get(integer));
        }
        return result;
    }

    @Override
    public Meal get(int id) {
        return MEAL.get(id);
    }

    @Override
    public Meal create(int id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(id, dateTime, description, calories);
        atomicCount.addAndGet(1);
        return meal;
    }

    @Override
    public void put(int id, Meal meal) {
        MEAL.put(id, meal);
    }

    public static AtomicInteger getAtomicCount() {
        return atomicCount;
    }
}
