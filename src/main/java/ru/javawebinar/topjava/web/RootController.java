package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMealService mealService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }
/*

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", mealService.getAll(LoggedUser.id()));
        return "mealList";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String setMeal(HttpServletRequest request) {
        int mealId = Integer.valueOf(request.getParameter("mealId"));
        UserMeal meal = new UserMeal(LocalDateTime.now(),
                                     request.getParameter("description"),
                                     Integer.parseInt(request.getParameter("calories")));

        if (mealId == 0) {
            mealService.save(meal, LoggedUser.id());
        } else {
            mealService.update(meal, LoggedUser.id());
        }
        return "mealList";
    }
*/

}
