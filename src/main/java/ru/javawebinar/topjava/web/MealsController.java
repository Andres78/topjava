package ru.javawebinar.topjava.web;

import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by herrero on 12.04.16.
 */
@Controller
public class MealsController {
    private static final Logger log = LoggerFactory.getLogger(MealsController.class);

//    @Autowired
//    private UserMealService service;
//
    @Autowired
    private UserMealRestController mealsController;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String getMeals(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("mealList", mealsController.getAll());
        return "mealList";
    }

    @RequestMapping(value = "/oneMeal", method = RequestMethod.GET)
    public String addMealForm(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        final UserMeal meal;
        if (request.getParameter("id") == null) meal = new UserMeal(LocalDateTime.now(), "", 1000);
        else {
            Integer id = getId(request);
            meal = mealsController.get(id);
        }
        request.setAttribute("meal", meal);
//        resetFilterParams(request);
        return "mealEdit";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String addMeal(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        final UserMeal userMeal = new UserMeal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()) {
            log.info("Create {}", userMeal);
            mealsController.create(userMeal);
        } else {
            log.info("Update {}", userMeal);
            int id = getId(request);
            userMeal.setId(id);
            mealsController.update(userMeal, id);
        }
//        if (resetFilterParams(request)) return "redirect:filteredMeal";
        return "redirect:meals";
    }

    @RequestMapping(value = "/deleteMeal", method = RequestMethod.GET)
    public String deleteMeal(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int id = getId(request);
        log.info("Delete {}", id);
        mealsController.delete(id);
//        if (resetFilterParams(request)) return "filteredMeal";
        return "redirect:meals";
    }

    @RequestMapping(value = "/filteredMeal", method = RequestMethod.POST)
    public String filterMeals(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request));
        List<UserMealWithExceed> meals = mealsController.getBetween(startDate, startTime, endDate, endTime);
        request.setAttribute("mealList", meals);
        return "mealList";
    }

//    private boolean resetFilterParams(HttpServletRequest request) {
//        if (request.getParameter("startDate")==null && request.getParameter("startDate")==null
//                && request.getParameter("startDate")==null && request.getParameter("startDate")==null)
//            return false;
//        resetParam("startDate", request);
//        resetParam("endDate", request);
//        resetParam("startTime", request);
//        resetParam("endTime", request);
//        return true;
//    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"), "parameter id  must not be null");
        return Integer.valueOf(paramId);
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
