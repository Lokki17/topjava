package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.dao.MapMealDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MockDB {

    private static MockDB instance;
    private static final Map<Integer, Meal> MEAL = new HashMap<>();

    private static MapMealDAOImpl mapMealDAO = new MapMealDAOImpl();

    private MockDB() {
        AtomicInteger integer = mapMealDAO.getInteger();
        //Meal meal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        MEAL.put(integer.get(), new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, integer.get()));
        integer = mapMealDAO.getInteger();
        MEAL.put(integer.get(), new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, integer.get()));
        integer = mapMealDAO.getInteger();
        MEAL.put(integer.get(),new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, integer.get()));
        integer = mapMealDAO.getInteger();
        MEAL.put(integer.get(), new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, integer.get()));
        integer = mapMealDAO.getInteger();
        MEAL.put(integer.get(), new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, integer.get()));
        integer = mapMealDAO.getInteger();
        MEAL.put(integer.get(), new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, integer.get()));
    }

    public synchronized static MockDB getInstance() {
        if (instance == null){
            instance = new MockDB();
        }
        return instance;
    }

    public Map<Integer, Meal> getMEAL() {
        return MEAL;
    }
}
