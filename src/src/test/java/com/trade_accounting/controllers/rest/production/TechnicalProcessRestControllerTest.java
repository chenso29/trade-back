package src.test.java.com.trade_accounting.controllers.rest.production;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.production.TechnicalProcessRestController;
import com.trade_accounting.models.dto.production.TechnicalProcessDto;
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

import java.util.HashSet;
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
@Sql(value = "/TechnicalProcess-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class TechnicalProcessRestControllerTest {

    @Autowired
    TechnicalProcessRestController technicalProcessRestController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(technicalProcessRestController, "TechnicalProcessRestController is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/technical/process"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        String technicalProcessDtoJson = new Gson().toJson(TechnicalProcessDto.builder()
                .id(1L)
                .name("Основной тех.процесс")
                .description("Тестовое описание ТП")
                .isArchived(false)
                .isShared(false)
                .departmentOwnerId(1L)
                .employeeOwnerId(2L)
                .dateOfChanged("2021-10-11 08:00")
                .employeeWhoLastChangedId(3L)
                .build());

        mockMvc.perform(get("/api/technical/process/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalProcessDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        Set<Long> stagesProductions = new HashSet<>();
        String technicalProcessDtoJson = new Gson().toJson(TechnicalProcessDto.builder()
                .id(3L)
                .name("Основной тех.процесс")
                .description("Тестовое описание ТП")
                .stagesProductionIds(stagesProductions)
                .isArchived(false)
                .isShared(false)
                .departmentOwnerId(2L)
                .employeeOwnerId(2L)
                .dateOfChanged("2021-10-11 08:00")
                .employeeWhoLastChangedId(3L)
                .build());

        mockMvc.perform(post("/api/technical/process")
                        .contentType(MediaType.APPLICATION_JSON).content(technicalProcessDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalProcessDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical/process"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testUpdate() throws Exception {
        Set<Long> stagesProductions = new HashSet<>();
        String technicalProcessDtoJson = new Gson().toJson(TechnicalProcessDto.builder()
                .id(1L)
                .name("Основной тех.процесс")
                .description("Тестовое описание ТП")
                .stagesProductionIds(stagesProductions)
                .isArchived(false)
                .isShared(false)
                .departmentOwnerId(2L)
                .employeeOwnerId(2L)
                .dateOfChanged("2021-10-11 08:00")
                .employeeWhoLastChangedId(3L)
                .build());

        mockMvc.perform(put("/api/technical/process")
                        .contentType(MediaType.APPLICATION_JSON).content(technicalProcessDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalProcessDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical/process"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/technical/process/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical/process"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
