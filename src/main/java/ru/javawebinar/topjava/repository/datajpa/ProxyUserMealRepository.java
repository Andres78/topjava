package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by herrero on 05.04.16.
 */
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

//    UserMeal saveWithUserId(Integer userId);

    int deleteByIdAndUserId(Integer id
    );

    UserMeal findOneByIdAndUserId(Integer id, Integer userId);

    List<UserMeal> getAllByUserIdOrderByDateTimeDesc(Integer userId);

    List<UserMeal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId, LocalDateTime startDate, LocalDateTime endDate);



}
