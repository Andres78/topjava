package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    @Autowired
    ProxyUserMealRepository proxy;

    @Autowired
    DataJpaUserRepositoryImpl userRepository;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User user = userRepository.get(userId);
        if (user != null && user.getId() == userId)
            userMeal.setUser(user);
        else return null;
        if (userMeal.isNew()) return proxy.save(userMeal);
        //else return proxy.updateUserMeal(userMeal);
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal userMeal = proxy.getOne(id);
        if (userMeal.getUser().getId() == userId) return userMeal;
        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.getAllByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(userId, startDate, endDate);
    }
}
