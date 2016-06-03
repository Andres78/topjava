package es.ahs.mealscounter.repository.jdbc;

import es.ahs.mealscounter.Profiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import es.ahs.mealscounter.model.UserMeal;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *  Andrey Kuznetsov

 */

@Repository
@Profile(Profiles.HSQLDB)
public class TimestampJdbcUserMealRepositoryImpl extends AbstractJdbcUserMealRepositoryImpl<Timestamp> {

    @Autowired
    public TimestampJdbcUserMealRepositoryImpl(DataSource dataSource) {
        super((rs, rowNum) ->
                new UserMeal(rs.getInt("id"), rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getString("description"), rs.getInt("calories")), dataSource
        );
    }

    @Override
    protected Timestamp toDbValue(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt);
    }
}