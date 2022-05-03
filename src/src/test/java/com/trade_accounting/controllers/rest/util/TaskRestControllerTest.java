package src.test.java.com.trade_accounting.controllers.rest.util;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.util.TaskRestController;
import com.trade_accounting.models.dto.util.TaskDto;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/task-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class TaskRestControllerTest {

    @Autowired
    private TaskRestController taskRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception{
        assertNotNull(taskRestController, "Task Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetById() throws Exception {
        String taskJson = new Gson().toJson(TaskDto.builder()
                .id(1L)
                .completed(true)
                .creationDateTime("2021-07-31 09:03:48")
                .deadlineDateTime("2021-09-24 09:03:48")
                .description("Описание задачи номер 0.")
                .taskAuthorId(5L)
                .employeeId(4L)
                .build());
        mockMvc.perform(get("/api/tasks/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(taskJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testCreate() throws Exception {
        String createdTaskJson = new Gson().toJson(TaskDto.builder()
                .id(4L)
                .completed(true)
                .creationDateTime("2021-07-31 09:03:48")
                .deadlineDateTime("2021-09-24 09:03:48")
                .description("Описание задачи номер 4.")
                .taskAuthorId(1L)
                .employeeId(1L)
                .taskCommentsIds(List.of())
                .build()
        );

        mockMvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(createdTaskJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        String updatedTaskJson = new Gson().toJson(TaskDto.builder()
                .id(3L)
                .completed(false)
                .creationDateTime("2021-07-31 09:03:48")
                .deadlineDateTime("2021-09-24 09:03:48")
                .description("Описание задачи номер 4.")
                .taskAuthorId(2L)
                .employeeId(2L)
                .taskCommentsIds(List.of())
                .build()
        );

        mockMvc.perform(put("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(updatedTaskJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/tasks/3"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}