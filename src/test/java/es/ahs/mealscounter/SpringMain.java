package es.ahs.mealscounter;

import org.springframework.context.support.GenericXmlApplicationContext;
import es.ahs.mealscounter.to.UserMealWithExceed;
import es.ahs.mealscounter.web.meal.AbstractUserMealController;
import es.ahs.mealscounter.web.meal.UserMealRestController;
import es.ahs.mealscounter.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 *  Andrey Kuznetsov
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.ACTIVE_DB, Profiles.DB_IMPLEMENTATION);
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml");
            appCtx.refresh();

            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserController.get(UserTestData.USER_ID));
            System.out.println();

            AbstractUserMealController mealController = appCtx.getBean(UserMealRestController.class);
            List<UserMealWithExceed> filteredMealsWithExceeded =
                    mealController.getBetween(
                            LocalDate.of(2016, Month.MAY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2016, Month.MAY, 31), LocalTime.of(11, 0));
            filteredMealsWithExceeded.forEach(System.out::println);
        }
    }
}
