package integration;

import com.test.spring.config.SecurityConfig;
import com.test.spring.config.SpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        SpringConfig.class,
        SecurityConfig.class
})
@WebAppConfiguration
public class MainControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Disabled
    @Test
    public void viewShouldReturnHomePage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").with(anonymous()));
    }
}
