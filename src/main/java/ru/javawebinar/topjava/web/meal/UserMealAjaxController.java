package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.converter.LocalDateFormatter;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(value = "/ajax/profile/meals")
public class UserMealAjaxController extends AbstractUserMealController {
    private static final Logger log = LoggerFactory.getLogger(UserMealAjaxController.class);


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        log.info(" getAll");
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        log.info(" delete {}", id);
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> updateOrCreate(@Valid MealTo mealTo, BindingResult result) {
        log.info(" mealTo {}", mealTo);
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (mealTo.getId() == 0) {
            super.create(UserMealsUtil.createFromTo(mealTo));
        } else {
            super.update(mealTo, mealTo.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) String startDate, @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endDate", required = false) String endDate, @RequestParam(value = "endTime", required = false) String endTime) {
        log.info(" /filter");
        LocalDate _startDate = null, _endDate = null;
        LocalTime _startTime = null, _endTime = null;
        if (null != startDate && !startDate.equals(""))  _startDate  = LocalDate.parse(startDate);
        if (null != endDate && !endDate.equals(""))    _endDate    = LocalDate.parse(endDate);
        if (null != startTime && !startTime.equals(""))  _startTime  = LocalTime.parse(startTime);
        if (null != endTime && !endTime.equals(""))    _endTime    = LocalTime.parse(endTime);
        return super.getBetween(_startDate, _startTime, _endDate, _endTime);
//        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}