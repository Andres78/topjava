package es.ahs.mealscounter.web.meal;

import es.ahs.mealscounter.model.UserMeal;
import es.ahs.mealscounter.service.UserMealService;
import es.ahs.mealscounter.to.UserMealWithExceed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import es.ahs.mealscounter.LoggedUser;
import es.ahs.mealscounter.util.TimeUtil;
import es.ahs.mealscounter.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Andrey Kuznetsov
 * 06.03.2016.
 */
public abstract class AbstractUserMealController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractUserMealController.class);

    @Autowired
    private UserMealService service;

    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        LOG.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<UserMealWithExceed> getAll() {
        int userId = LoggedUser.id();
        LOG.info("getAll for User {}", userId);
        return UserMealsUtil.getWithExceeded(service.getAll(userId), LoggedUser.getCaloriesPerDay());
    }

    public void update(UserMeal meal, int id) {
        meal.setId(id);
        int userId = LoggedUser.id();
        LOG.info("update {} for User {}", meal, userId);
        service.update(meal, userId);
    }

    public UserMeal create(UserMeal meal) {
        meal.setId(null);
        int userId = LoggedUser.id();
        LOG.info("create {} for User {}", meal, userId);
        return service.save(meal, userId);
    }

    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = LoggedUser.id();
        LOG.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);
        return UserMealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : TimeUtil.MIN_DATE, endDate != null ? endDate : TimeUtil.MAX_DATE, userId),
                        startTime != null ? startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX, LoggedUser.getCaloriesPerDay()
        );
    }
}