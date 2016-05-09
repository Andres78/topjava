package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public abstract class AbstractUserMealController {
    private static final Logger log = LoggerFactory.getLogger(AbstractUserMealController.class);

    @Autowired
    private UserMealService service;

    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        log.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        log.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<UserMealWithExceed> getAll() {
        int userId = LoggedUser.id();
        log.info("getAll for User {}", userId);
        return UserMealsUtil.getWithExceeded(service.getAll(userId), LoggedUser.getCaloriesPerDay());
    }

    public void update(UserMeal meal, int id) {
        meal.setId(id);
        int userId = LoggedUser.id();
        log.info("update {} for User {}", meal, userId);
        service.update(meal, userId);
    }

    public void update(MealTo mealTo, int id) {
        UserMeal um = new UserMeal(mealTo.getId(), mealTo.getDateTime(), mealTo.getDescription(), mealTo.getCalories());
        log.info("updaeTo {} ", mealTo);
        update(um, id);
    }

    public UserMeal create(UserMeal meal) {
        meal.setId(null);
        int userId = LoggedUser.id();
        log.info("create {} for User {}", meal, userId);
        return service.save(meal, userId);
    }

    public void create(MealTo mealTo) {
        log.info("createTo {}", mealTo);
        UserMeal um = new UserMeal(mealTo.getDateTime(), mealTo.getDescription(), mealTo.getCalories());
        create(um);
    }

    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = LoggedUser.id();
        log.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);
        return UserMealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : TimeUtil.MIN_DATE, endDate != null ? endDate : TimeUtil.MAX_DATE, userId),
                        startTime != null ? startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX, LoggedUser.getCaloriesPerDay()
        );
    }
}