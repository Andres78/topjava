package es.ahs.mealscounter.service.jdbc;

import es.ahs.mealscounter.Profiles;
import org.springframework.test.context.ActiveProfiles;
import es.ahs.mealscounter.service.AbstractUserMealServiceTest;

@ActiveProfiles(Profiles.JDBC)
public class JdbcUserMealServiceTest extends AbstractUserMealServiceTest {
}