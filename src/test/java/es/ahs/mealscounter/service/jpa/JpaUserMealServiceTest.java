package es.ahs.mealscounter.service.jpa;

import es.ahs.mealscounter.Profiles;
import org.springframework.test.context.ActiveProfiles;
import es.ahs.mealscounter.service.AbstractUserMealServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaUserMealServiceTest extends AbstractUserMealServiceTest {
}