package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.Profiles.POSTGRES;

/**
 * Created by herrero on 08.04.16.
 */
@ActiveProfiles({POSTGRES, DATAJPA})
public class DataJpaUserTest extends UserServiceTest {
}
