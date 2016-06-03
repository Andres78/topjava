package es.ahs.mealscounter.service.datajpa;

import es.ahs.mealscounter.MealTestData;
import es.ahs.mealscounter.Profiles;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import es.ahs.mealscounter.model.User;
import es.ahs.mealscounter.service.AbstractJpaUserServiceTest;
import es.ahs.mealscounter.util.exception.NotFoundException;

import static es.ahs.mealscounter.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithMeals(USER_ID);
        MATCHER.assertEquals(USER, user);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS, user.getMeals());
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() throws Exception {
        service.getWithMeals(1);
    }
}