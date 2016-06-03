package es.ahs.mealscounter.service;

import es.ahs.mealscounter.repository.JpaUtil;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Andrey Kuznetsov
 * 07.04.2016.
 */
abstract public class AbstractJpaUserServiceTest extends AbstractUserServiceTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JpaUtil jpaUtil;

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
