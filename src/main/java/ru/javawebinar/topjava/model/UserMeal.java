package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 * NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT um FROM UserMeal um"),
 *         NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId"),

 */
@NamedQueries({
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId"),
        @NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId AND um.dateTime BETWEEN :startDate AND :endDate" ),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId ORDER BY um.dateTime DESC"),
})
@Entity
@Table(name = "meals")
public class UserMeal extends BaseEntity {
    public static final String CREATE = "UserMeal.create";
    public static final String GET_BETWEEN = "UserMeal.getbetween";
    public static final String DELETE = "UserMeal.delete";
    public static final String ALL_SORTED = "UserMeal.getAllSorted";

    @Column(name = "calories")
    @Digits(fraction = 0, integer = 4)
    protected int calories;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "description")
    private String description;

//    @Column(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY/*, cascade = {CascadeType.MERGE, CascadeType.PERSIST}*/)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
