package es.ahs.mealscounter.repository.datajpa;

import es.ahs.mealscounter.model.BaseEntity;
import es.ahs.mealscounter.model.User;
import es.ahs.mealscounter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.validation.ValidationException;
import java.util.List;

/**
 * Andrey Kuznetsov
 * 27.03.2016.
 */

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort("name", "email");

    @Autowired
    private ProxyUserRepository proxy;

    public void checkModificationAllowed(Integer id) {
        if (id != null && id < BaseEntity.START_SEQ + 2) {
            throw new ValidationException("Admin/User modification is not allowed. <br><br><a class=\"btn btn-primary btn-lg\" role=\"button\" href=\"register\">Register &raquo;</a> your own please.");
        }
    }

    @Override
    public User save(User user) {
        checkModificationAllowed(user.getId());
        return proxy.save(user);
    }

    @Override
    public boolean delete(int id) {
        checkModificationAllowed(id);
        return proxy.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return proxy.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return proxy.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return proxy.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User getWithMeals(int id) {
        return proxy.getWithMeals(id);
    }
}
