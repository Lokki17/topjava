package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MapMealDAOImpl;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MockDB;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private MealDAO mealDAO = new MapMealDAOImpl();

    public MealServlet() {
        //this.mealDAO = new MapMealDAOImpl();
    }

    private static String MEALS_LIST = "/mealList.jsp";
    private static String EDIT_MEAL = "/mealEdit.jsp";
    private static String ADD_MEAL = "/mealAdd.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals" + " ");

        String forward = MEALS_LIST;
        String action = request.getParameter("action");

        if ("edit".equalsIgnoreCase(action)){
            forward = EDIT_MEAL;
            int mealId = Integer.parseInt(request.getParameter("meal"));
            Meal meal = mealDAO.getMeal(mealId);
            request.setAttribute("meal", meal);
        } else if ("delete".equalsIgnoreCase(action)){
            int mealId = Integer.parseInt(request.getParameter("meal"));
            mealDAO.deleteMeal(mealId);
        } else if ("add".equalsIgnoreCase(action)){
            forward = ADD_MEAL;
        }

        List<Meal> list = mealDAO.getMealList();

        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(list, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);

        request.setAttribute("meals", meals);
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("do Post");

        request.setCharacterEncoding("UTF-8");

        String create = request.getParameter("create");
        String dateTimeStr = request.getParameter("dateTime");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr.replace("T", " "), formatter);

        if (create != null){
            mealDAO.createMeal(localDateTime, description, calories);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            mealDAO.updateMeal(id, localDateTime, description, calories);
        }

        doGet(request, response);
    }
}
