package es.ahs.mealscounter.web.meal;

import es.ahs.mealscounter.MealTestData;
import es.ahs.mealscounter.service.UserMealService;
import es.ahs.mealscounter.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import es.ahs.mealscounter.model.UserMeal;
import es.ahs.mealscounter.util.UserMealsUtil;
import es.ahs.mealscounter.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static es.ahs.mealscounter.TestUtil.userHttpBasic;
import static es.ahs.mealscounter.UserTestData.*;
import static es.ahs.mealscounter.model.BaseEntity.START_SEQ;

public class UserMealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserMealRestController.REST_URL + '/';

    @Autowired
    private UserMealService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + MealTestData.ADMIN_MEAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MealTestData.MATCHER.contentMatcher(MealTestData.ADMIN_MEAL));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + MealTestData.MEAL1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + MealTestData.ADMIN_MEAL_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + MealTestData.ADMIN_MEAL_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + MealTestData.MEAL1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MealTestData.MEAL6, MealTestData.MEAL5, MealTestData.MEAL4, MealTestData.MEAL3, MealTestData.MEAL2), service.getAll(START_SEQ));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = MealTestData.getUpdated();

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + MealTestData.MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(MealTestData.MEAL1_ID, START_SEQ));
    }

    @Test
    public void testCreate() throws Exception {
        UserMeal created = MealTestData.getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        UserMeal returned = MealTestData.MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MealTestData.MATCHER.assertEquals(created, returned);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MealTestData.ADMIN_MEAL2, created, MealTestData.ADMIN_MEAL), service.getAll(ADMIN_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MealTestData.MATCHER_WITH_EXCEED.contentListMatcher(UserMealsUtil.getWithExceeded(MealTestData.USER_MEALS, USER.getCaloriesPerDay())));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "between?startDateTime=2016-05-30T07:00&endDateTime=2016-05-31T11:00:00")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MealTestData.MATCHER_WITH_EXCEED.contentListMatcher(
                        UserMealsUtil.createWithExceed(MealTestData.MEAL4, true),
                        UserMealsUtil.createWithExceed(MealTestData.MEAL1, false)));
    }

    @Test
    public void testFilter() throws Exception {
        mockMvc.perform(get(REST_URL + "filter?startDate=2016-05-30&startTime=07:00&endDate=2016-05-31&endTime=11:00")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MealTestData.MATCHER_WITH_EXCEED.contentListMatcher(
                        UserMealsUtil.createWithExceed(MealTestData.MEAL4, true),
                        UserMealsUtil.createWithExceed(MealTestData.MEAL1, false)));
    }

    @Test
    public void testFilterAll() throws Exception {
        mockMvc.perform(get(REST_URL + "filter?startDate=&endTime=")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MealTestData.MATCHER_WITH_EXCEED.contentListMatcher(
                        UserMealsUtil.getWithExceeded(Arrays.asList(MealTestData.MEAL6, MealTestData.MEAL5, MealTestData.MEAL4, MealTestData.MEAL3, MealTestData.MEAL2, MealTestData.MEAL1), USER.getCaloriesPerDay())));
    }
}