package es.ahs.mealscounter.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import es.ahs.mealscounter.Profiles;
import es.ahs.mealscounter.model.UserMeal;

import javax.sql.DataSource;
import java.time.LocalDateTime;

/**
 *  Andrey Kuznetsov
*
 */

@Repository
@Profile(Profiles.POSTGRES)
public class Java8JdbcUserMealRepositoryImpl extends AbstractJdbcUserMealRepositoryImpl<LocalDateTime> {

    @Autowired
    public Java8JdbcUserMealRepositoryImpl(DataSource dataSource) {
        super(BeanPropertyRowMapper.newInstance(UserMeal.class), dataSource);
    }

    @Override
    protected LocalDateTime toDbValue(LocalDateTime ldt) {
        return ldt;
    }
}