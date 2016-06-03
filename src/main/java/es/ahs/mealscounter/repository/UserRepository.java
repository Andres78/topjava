package es.ahs.mealscounter.repository;

import es.ahs.mealscounter.model.User;

import java.util.List;

/**
 *  Andrey Kuznetsov

 */
public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    default User getWithMeals(int id){
        throw new UnsupportedOperationException();
    }
}
