package es.ahs.mealscounter.service;

import es.ahs.mealscounter.MealTestData;
import es.ahs.mealscounter.UserTestData;
import es.ahs.mealscounter.model.UserMeal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import es.ahs.mealscounter.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

abstract public class AbstractUserMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserMealService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(MealTestData.MEAL1_ID, UserTestData.USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MealTestData.MEAL6, MealTestData.MEAL5, MealTestData.MEAL4, MealTestData.MEAL3, MealTestData.MEAL2), service.getAll(UserTestData.USER_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MealTestData.MEAL1_ID, 1);
    }

    @Test
    public void testSave() throws Exception {
        UserMeal created = MealTestData.getCreated();
        service.save(created, UserTestData.USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(created, MealTestData.MEAL6, MealTestData.MEAL5, MealTestData.MEAL4, MealTestData.MEAL3, MealTestData.MEAL2, MealTestData.MEAL1), service.getAll(UserTestData.USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        UserMeal actual = service.get(MealTestData.ADMIN_MEAL_ID, UserTestData.ADMIN_ID);
        MealTestData.MATCHER.assertEquals(MealTestData.ADMIN_MEAL, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MealTestData.MEAL1_ID, UserTestData.ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = MealTestData.getUpdated();
        service.update(updated, UserTestData.USER_ID);
        MealTestData.MATCHER.assertEquals(updated, service.get(MealTestData.MEAL1_ID, UserTestData.USER_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        UserMeal item = service.get(MealTestData.MEAL1_ID, UserTestData.USER_ID);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + MealTestData.MEAL1_ID);
        service.update(item, UserTestData.ADMIN_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS, service.getAll(UserTestData.USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MealTestData.MEAL3, MealTestData.MEAL2, MealTestData.MEAL1),
                service.getBetweenDates(LocalDate.of(2016, Month.MAY, 30), LocalDate.of(2016, Month.MAY, 30), UserTestData.USER_ID));
    }
}