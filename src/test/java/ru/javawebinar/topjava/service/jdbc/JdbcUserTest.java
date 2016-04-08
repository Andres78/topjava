package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.Profiles.POSTGRES;

/**
 * Created by herrero on 08.04.16.
 */
@ActiveProfiles({POSTGRES, JDBC})
public class JdbcUserTest extends UserServiceTest {
}
