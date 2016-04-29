package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ahs on 29.04.16.
 */
@RestController
@RequestMapping("/ajax/profile/meals")
public class UserMealAjaxController extends AbstractUserMealController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@RequestParam("id") int id,
                               @RequestParam("date_time") String ldt, //LocalDateTime ldt,
                               @RequestParam("description") String desc,
                               @RequestParam("calories") int calories) {
        LocalDateTime _ldt = LocalDateTime.parse(ldt);
        UserMeal meal = new UserMeal(_ldt, desc, calories);
        if (id == 0) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }
    }

}
