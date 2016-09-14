package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class MapMealDAOImpl implements MealDAO {

    private static final Map<Integer, Meal> MEAL = new ConcurrentHashMap<>();

    private static AtomicInteger atomicCount = new AtomicInteger();

    public MapMealDAOImpl() {
            MEAL.put(atomicCount.get(), create(LocalDateTime.of(2016, Month.MAY, 30, 13, 0), "Обед", 1000));
            MEAL.put(atomicCount.get(), create(LocalDateTime.of(2016, Month.MAY, 30, 20, 0), "Ужин", 500));
            MEAL.put(atomicCount.get(), create(LocalDateTime.of(2016, Month.MAY, 31, 10, 0), "Завтрак", 1000));
            MEAL.put(atomicCount.get(), create(LocalDateTime.of(2016, Month.MAY, 31, 13, 0), "Обед", 500));
            MEAL.put(atomicCount.get(), create(LocalDateTime.of(2016, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void update(Meal meal) {
        MEAL.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        MEAL.remove(id);
        }

    @Override
    public List<Meal> getList() {
        return new ArrayList<>(MEAL.values());
    }

    @Override
    public Meal get(int id) {
        return MEAL.get(id);
    }

    @Override
    public Meal create(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(atomicCount.get(), dateTime, description, calories);
        MEAL.put(meal.getId(), meal);
        atomicCount.incrementAndGet();
        return meal;
    }
}
