package es.ahs.mealscounter.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import es.ahs.mealscounter.LoggedUser;
import es.ahs.mealscounter.model.User;
import es.ahs.mealscounter.to.UserTo;

/**
 * Andrey Kuznetsov
 * 06.03.2016.
 */
@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    public static final String REST_URL = "/rest/profile";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(LoggedUser.id());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        super.delete(LoggedUser.id());
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserTo userTo) {
        userTo.setId(LoggedUser.id());
        super.update(userTo);
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String testUTF() {
        return "Русский текст";
    }
}