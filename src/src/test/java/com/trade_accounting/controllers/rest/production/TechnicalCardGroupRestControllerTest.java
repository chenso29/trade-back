package src.test.java.com.trade_accounting.controllers.rest.production;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.production.TechnicalCardGroupRestController;
import com.trade_accounting.models.dto.production.TechnicalCardGroupDto;
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
@Sql(value = "/TechnicalCardGroup-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class TechnicalCardGroupRestControllerTest {

    @Autowired
    private TechnicalCardGroupRestController technicalCardGroupRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(technicalCardGroupRestController, "TechnicalCardGroup Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/technical_card_group"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        TechnicalCardGroupDto technicalCardGroupDto = TechnicalCardGroupDto.builder()
                .id(1L)
                .comment("Комментарий 1")
                .name("Группа технических карт №1")
                .sortNumber("1")
                .build();

        String technicalCardGroupDtoJson = new Gson().toJson(technicalCardGroupDto);

        mockMvc.perform(get("/api/technical_card_group/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        TechnicalCardGroupDto technicalCardGroupDto = TechnicalCardGroupDto.builder()
                .id(4L)
                .comment("Комментарий 1")
                .name("Группа технических карт №1")
                .sortNumber("1")
                .build();

        String technicalCardGroupDtoJson = new Gson().toJson(technicalCardGroupDto);

        mockMvc.perform(post("/api/technical_card_group").contentType(MediaType.APPLICATION_JSON).content(technicalCardGroupDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical_card_group"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        TechnicalCardGroupDto technicalCardGroupDto = TechnicalCardGroupDto.builder()
                .id(3L)
                .comment("Комментарий 1")
                .name("Группа технических карт №1")
                .sortNumber("1")
                .build();

        String technicalCardGroupDtoJson = new Gson().toJson(technicalCardGroupDto);

        mockMvc.perform(put("/api/technical_card_group").contentType(MediaType.APPLICATION_JSON).content(technicalCardGroupDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical_card_group"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/technical_card_group/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical_card_group"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
