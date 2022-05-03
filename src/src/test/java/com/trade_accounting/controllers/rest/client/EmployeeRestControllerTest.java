package src.test.java.com.trade_accounting.controllers.rest.client;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.client.EmployeeRestController;
import com.trade_accounting.models.dto.client.EmployeeDto;
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

import java.util.Set;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/employee-before.sql")
@WithMockUser(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class EmployeeRestControllerTest {
    @Autowired
    EmployeeRestController employeeRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(employeeRestController, "Employee Rest controller is null");
    }
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
    @Test
    public void testGetById() throws Exception {
        String employeeJson = new Gson().toJson(EmployeeDto.builder()
                .id(1L)
                .description("Some special text about Vasya")
                .email("vasyaogon@mail.ru")
                .firstName("Vasya")
                .inn("526317984689")
                .lastName("Vasiliev")
                .middleName("Vasilievich")
                .password("12345")
                .phone("+7(999)111-22-33")
                .sortNumber("1")
        );
        mockMvc.perform(get("/api/employee/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(employeeJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
    @Test
    public void testCreate() throws Exception {
        EmployeeDto createdEmployee = EmployeeDto.builder()
                .lastName("created")
                .firstName("created")
                .middleName("created")
                .sortNumber("created")
                .phone("created")
                .inn("012341234234")
                .description("created")
                .email("created")
                .password("created")
                .departmentDtoId(4L)
                .imageDtoId(1L)
                .positionDtoId(4L)
                .roleDtoIds(Set.of(2L))
                .build();
        String createdEmployeeJson = new Gson().toJson(createdEmployee);
        mockMvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON)
                        .content(createdEmployeeJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdEmployeeJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }
    @Test
    public void testUpdate() throws Exception {
        EmployeeDto updatedEmployee = EmployeeDto.builder()
                .id(3L)
                .lastName("updated")
                .firstName("updated")
                .middleName("updated")
                .sortNumber("updated")
                .phone("updated")
                .inn("012345678903")
                .description("updated")
                .email("updated")
                .password("updated")
                .departmentDtoId(4L)
                .imageDtoId(1L)
                .positionDtoId(4L)
                .roleDtoIds(Set.of(2L))
                .build();
        String updatedEmployeeJson = new Gson().toJson(updatedEmployee);
        mockMvc.perform(put("/api/employee").contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedEmployeeJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/employee/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
