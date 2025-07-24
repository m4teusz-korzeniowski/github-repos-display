package korzeniowski.mateusz.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GitHubRepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRepos_ShouldReturnUserRepositoriesAndShouldReturn200() throws Exception {
        // given
        String username = "m4teusz-korzeniowski";

        // when
        this.mockMvc.perform(get("/" + username + "/repos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[*].ownerName", everyItem(is(username))))
                .andExpect(jsonPath("$[0].branches").isArray())
                .andExpect(jsonPath("$[0].branches[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].branches[0].lastCommitSha").isNotEmpty());
    }
}
