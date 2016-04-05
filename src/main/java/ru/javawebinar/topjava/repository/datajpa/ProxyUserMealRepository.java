package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by herrero on 05.04.16.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

//    UserMeal saveWithUserId(Integer userId);


    @Override
    @Transactional
    UserMeal save(UserMeal userMeal);

//    @Transactional
//    UserMeal updateUserMeal(UserMeal userMeal);

    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
//    int deleteByIdAndUserId(Integer id);

    UserMeal getOne(Integer id);

    List<UserMeal> getAllByUserIdOrderByDateTimeDesc(Integer userId);

    List<UserMeal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId, LocalDateTime startDate, LocalDateTime endDate);



}
