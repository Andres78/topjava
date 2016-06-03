package es.ahs.mealscounter.service.datajpa;

import es.ahs.mealscounter.MealTestData;
import es.ahs.mealscounter.Profiles;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import es.ahs.mealscounter.UserTestData;
import es.ahs.mealscounter.model.UserMeal;
import es.ahs.mealscounter.service.AbstractUserMealServiceTest;
import es.ahs.mealscounter.util.exception.NotFoundException;

import static es.ahs.mealscounter.UserTestData.ADMIN_ID;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserMealServiceTest extends AbstractUserMealServiceTest {
    @Test
    public void testGetWithUser() throws Exception {
        UserMeal adminMeal = service.getWithUser(MealTestData.ADMIN_MEAL_ID, ADMIN_ID);
        MealTestData.MATCHER.assertEquals(MealTestData.ADMIN_MEAL, adminMeal);
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, adminMeal.getUser());
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithUserNotFound() throws Exception {
        service.getWithUser(MealTestData.MEAL1_ID, ADMIN_ID);
    }
}