package es.ahs.mealscounter.service;


import es.ahs.mealscounter.to.UserTo;
import es.ahs.mealscounter.model.User;
import es.ahs.mealscounter.util.exception.NotFoundException;

import java.util.List;

/**
 *  Andrey Kuznetsov
 */
public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(UserTo user);

    List<User> getAll();

    void update(User user);

    void evictCache();

    void enable(int id, boolean enable);

    User getWithMeals(int id);
}
