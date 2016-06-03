package es.ahs.mealscounter.service.jdbc;

import es.ahs.mealscounter.Profiles;
import es.ahs.mealscounter.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}