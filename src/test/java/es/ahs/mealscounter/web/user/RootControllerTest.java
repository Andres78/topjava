package es.ahs.mealscounter.web.user;

import es.ahs.mealscounter.TestUtil;
import es.ahs.mealscounter.UserTestData;
import es.ahs.mealscounter.web.AbstractControllerTest;
import org.junit.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Andrey Kuznetsov
 * 10.04.2016.
 */
public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUserList() throws Exception {
        TestUtil.authorize(UserTestData.ADMIN);
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"));
    }

    @Test
    public void testUserListUnAuth() throws Exception {
        TestUtil.authorize(UserTestData.USER);
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("exception/exception"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/exception/exception.jsp"));
    }

    @Test
    public void testMealList() throws Exception {
        TestUtil.authorize(UserTestData.USER);
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"));
    }

    @Test
    public void testRoot() throws Exception {
        mockMvc.perform(formLogin("/spring_security_check").user(UserTestData.USER.getEmail()).password(UserTestData.USER.getPassword()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/meals"));
    }
}