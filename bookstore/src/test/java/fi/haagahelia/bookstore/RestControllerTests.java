package fi.haagahelia.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // RESTful service tests
    @Test
    void restTestGettingAllBooks() throws Exception {
        mockMvc.perform(get("/books")
                .with(user("testuser").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].title").value("Java Programming"))
                .andExpect(jsonPath("$[1].title").value("Spring Basics"));
    }

    @Test
    void restTestGettingBookById() throws Exception {
        mockMvc.perform(get("/books/1")
                .with(user("testuser").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java Programming"))
                .andExpect(jsonPath("$.author").value("John Doe"));
    }
}
