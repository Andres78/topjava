package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

import static ru.javawebinar.topjava.Profiles.*;

/**
 * Created by herrero on 08.04.16.
 */
@ActiveProfiles({POSTGRES, JDBC})
public class JdbcUserMealServiceTest extends UserMealServiceTest {
}
