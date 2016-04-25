package ru.javawebinar.topjava.web.meals;

import org.junit.Test;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by ahs on 25.04.16.
 */
public class UserMealControllerTest extends AbstractControllerTest {

    @Test
    public void testUserMealList() throws Exception {
        LoggedUser.setId(START_SEQ);
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealsList", hasSize(6)))
                .andExpect(model().attribute("mealsList", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ+7))//,
//                                hasProperty("user_id", is(START_SEQ))
//                                hasProperty("description", is())
                        )
                )));
    }
}
