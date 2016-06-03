package es.ahs.mealscounter.util;

import es.ahs.mealscounter.to.UserTo;
import es.ahs.mealscounter.model.Role;
import es.ahs.mealscounter.model.User;

/**
 * Andrey Kuznetsov
 */
public class UserUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), newUser.getCaloriesPerDay(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCaloriesPerDay());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setCaloriesPerDay(userTo.getCaloriesPerDay());
        user.setPassword(userTo.getPassword());
        return prepareToSave(user);
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
