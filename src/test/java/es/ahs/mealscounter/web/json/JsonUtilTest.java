package es.ahs.mealscounter.web.json;

import es.ahs.mealscounter.MealTestData;
import es.ahs.mealscounter.model.UserMeal;
import org.junit.Test;

import java.util.List;

/**
 * Andrey Kuznetsov
 * 22.07.2016.
 */
public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(MealTestData.ADMIN_MEAL);
        System.out.println(json);
        UserMeal userMeal = JsonUtil.readValue(json, UserMeal.class);
        MealTestData.MATCHER.assertEquals(MealTestData.ADMIN_MEAL, userMeal);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(MealTestData.USER_MEALS);
        System.out.println(json);
        List<UserMeal> userMeals = JsonUtil.readValues(json, UserMeal.class);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS, userMeals);
    }
}