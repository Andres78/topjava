package ru.javawebinar.topjava.repository.jpa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


/**
 * Created by herrero on 01.04.16.
 */


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))


public class JpaUserMealRepositoryImplTest {

    @Autowired
    UserMealRepository repository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void save() throws Exception {
        UserMeal created = getCreated();
        repository.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), repository.getAll(USER_ID));
    }
    @Test
    public void testDelete() throws Exception {
        repository.delete(MealTestData.MEAL1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), repository.getAll(USER_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        assertFalse(repository.delete(MEAL1_ID, 1));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal created = getCreated();
        repository.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), repository.getAll(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        UserMeal actual = repository.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        assertTrue(repository.get(MEAL1_ID, ADMIN_ID) == null);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();
        repository.save(updated, USER_ID);
        MATCHER.assertEquals(updated, repository.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        UserMeal item = repository.get(MEAL1_ID, USER_ID);
        assertFalse(repository.save(item, ADMIN_ID) != null);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(USER_MEALS, repository.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                repository.getBetween(LocalDateTime.of(2015, Month.MAY, 30,0,0), LocalDateTime.of(2015, Month.MAY, 30,23,59), USER_ID));
    }
    @Test
    public void delete() throws Exception {


    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void getBetween() throws Exception {

    }

}