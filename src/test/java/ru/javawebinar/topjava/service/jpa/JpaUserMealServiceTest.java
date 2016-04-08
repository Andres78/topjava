package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.Profiles.JPA;
import static ru.javawebinar.topjava.Profiles.POSTGRES;

/**
 * Created by herrero on 08.04.16.
 */
@ActiveProfiles({POSTGRES, JPA})
public class JpaUserMealServiceTest extends UserMealServiceTest {
}
