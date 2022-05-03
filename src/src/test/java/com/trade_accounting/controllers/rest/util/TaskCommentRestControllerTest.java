package src.test.java.com.trade_accounting.controllers.rest.util;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.util.TaskCommentRestController;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/TaskComment-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class TaskCommentRestControllerTest {

    @Autowired
    private TaskCommentRestController taskCommentRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception{
        assertNotNull(taskCommentRestController, "Task Comment Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/task_comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetById() throws Exception {
        String taskJson = new Gson().toJson(TaskCommentDto.builder()
                .id(3L)
                .commentContent("comm3")
                .taskId(2L)
                .publisherId(3L)
                .publishedDateTime("2014-04-10 03:09:02")
                .build());
        mockMvc.perform(get("/api/task_comments/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(taskJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testCreate() throws Exception {
        String createdTaskCommentJson = new Gson().toJson(TaskCommentDto.builder()
                .id(4L)
                .commentContent("comm4")
                .taskId(1L)
                .publisherId(3L)
                .publishedDateTime("2015-04-10 03:09:02")
                .build()
        );
        mockMvc.perform(post("/api/task_comments").contentType(MediaType.APPLICATION_JSON)
                .content(createdTaskCommentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdTaskCommentJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/task_comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        String updatedTaskCommentJson = new Gson().toJson(TaskCommentDto.builder().id(4L).commentContent("desc5")
                .taskId(1L)
                .publisherId(3L)
                .publishedDateTime("2016-04-10 03:09:02")
                .build()
        );

        mockMvc.perform(put("/api/task_comments").contentType(MediaType.APPLICATION_JSON)
                .content(updatedTaskCommentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedTaskCommentJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/task_comments/3"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/task_comments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}