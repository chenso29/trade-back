package src.test.java.com.trade_accounting.controllers.rest.client;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.client.DepartmentRestController;
import com.trade_accounting.models.dto.client.DepartmentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
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
@Sql(value = "/department-before.sql")
@WithMockUser
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class DepartmentRestControllerTest {

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(departmentRestController, "Department Rest controller is null");
    }
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/department"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
    @Test
    public void testGetById() throws Exception {
        String departmentJson = new Gson().toJson(DepartmentDto.builder().id(3L).name("name3")
                .sortNumber("sort num 3"));
        mockMvc.perform(get("/api/department/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(departmentJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
    @Test
    public void testCreate() throws Exception {
        DepartmentDto createdDepartment = DepartmentDto.builder().id(4L).name("created")
                .sortNumber("created").build();
        String createdDepartmentJson = new Gson().toJson(createdDepartment);
        mockMvc.perform(post("/api/department").contentType(MediaType.APPLICATION_JSON)
                .content(createdDepartmentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdDepartmentJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/department"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
    @Test
    public void testUpdate() throws Exception {
        DepartmentDto updatedDepartment = DepartmentDto.builder().id(3L).name("updated")
                .sortNumber("updated").build();
        String updatedDepartmentJson = new Gson().toJson(updatedDepartment);
        mockMvc.perform(put("/api/department").contentType(MediaType.APPLICATION_JSON)
                .content(updatedDepartmentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedDepartmentJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/department/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/department"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
