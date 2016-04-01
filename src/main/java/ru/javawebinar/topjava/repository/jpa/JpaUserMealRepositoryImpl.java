package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = false)
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User us_ref = em.getReference(User.class, userId);
//        Integer uid = userMeal.getUser().getId();
        if (null == us_ref) return null; // нет такого юзера вообще *** моэет проверять на ид...
        if (userMeal.getUser() == null) userMeal.setUser(us_ref); //еда была без юзера - даем ей юзера
        if(userMeal.getId() == null) { // если нет id у еды - она новая и мы её сохраняем
                                    // юзер или был или мы его уже добавили
//            userMeal.setUser(us_ref);
            em.persist(userMeal);
        }
//        else if(userId != userMeal.getUser().getId()) { // id у еды есть, мы ей записали её user'a, а его id не совпадает с id текущего - ошибка, получи Null
        else if(userId != userMeal.getUser().getId()) { // id у еды есть, user был сразу, а его id не совпадает с id текущего - ошибка, получи Null
//        else if(userId != us_ref.getId()) { // id у еды есть, user был сразу, а его id не совпадает с id текущего - ошибка, получи Null
            return null;
        } else // и ид есть и юзер совпал в еде совпал с тем который сохраняет - значит обновляем
         {
            userMeal.setUser(us_ref);
            em.merge(userMeal); // у еды id было, user'ы совпали - обновляем запись
        }
        return em.find(UserMeal.class, userMeal.getId()); // возвращаем то что записали
    }

    @Transactional(readOnly = false)
    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        UserMeal um = em.find(UserMeal.class, id);
        if (um.getUser().getId() != userId) return null;
        return um;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        if (startDate != null);
        em.clear();
        return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();

    }
}