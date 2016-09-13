package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MapMealDAOImpl;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private MealDAO mealDAO = new MapMealDAOImpl();

    public MealServlet() {
    }

    private final static String MEALS_LIST = "/mealList.jsp";
    private final static String ADD_EDIT_MEAL = "/mealAddEdit.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals" + " ");

        String forward = MEALS_LIST;
        String action = request.getParameter("action");

        if ("edit".equalsIgnoreCase(action)){
            forward = ADD_EDIT_MEAL;
            int mealId = Integer.parseInt(request.getParameter("meal"));
            Meal meal = mealDAO.get(mealId);
            request.setAttribute("meal", meal);
        } else if ("delete".equalsIgnoreCase(action)){
            int mealId = Integer.parseInt(request.getParameter("meal"));
            mealDAO.delete(mealId);
        } else if ("add".equalsIgnoreCase(action)){
            forward = ADD_EDIT_MEAL;
        }

        List<Meal> list = mealDAO.getList();

        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(list, LocalTime.MIN, LocalTime.MAX, 2000);

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

        String date = "";
        if (dateTimeStr.contains(".")){
            date = dateTimeStr.substring(0, dateTimeStr.lastIndexOf(":"));
        } else date = dateTimeStr;
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

        if (create != null){
            int id = MapMealDAOImpl.getAtomicCount().get();
            Meal meal = mealDAO.create(id, localDateTime, description, calories);
            mealDAO.put(id, meal);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            mealDAO.update(id, localDateTime, description, calories);
        }

        doGet(request, response);
    }
}
