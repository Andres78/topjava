package es.ahs.mealscounter.service.jpa;

import es.ahs.mealscounter.Profiles;
import org.springframework.test.context.ActiveProfiles;
import es.ahs.mealscounter.service.AbstractJpaUserServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaUserServiceTest extends AbstractJpaUserServiceTest {
}