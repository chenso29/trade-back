package src.test.java.com.trade_accounting.controllers.rest.production;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.production.TechnicalCardRestController;
import com.trade_accounting.services.impl.production.TechnicalCardServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/TechnicalCard-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithMockUser("karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class TechnicalCardRestControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private TechnicalCardRestController controller;

    @Autowired
    private TechnicalCardServiceImpl technicalCardService;

    @Test
    public void testExistence() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetById() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(1L));

        mockMvc.perform(get("/api/technical_card/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
//
    @Test
    public void testCreate() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(2L));

        mockMvc.perform(post("/api/technical_card").contentType(MediaType.APPLICATION_JSON)
                .content(technicalCardDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testUpdate() throws Exception {
        String technicalCardDtoJson = new Gson().toJson(technicalCardService.getById(3L));

        mockMvc.perform(put("/api/technical_card").contentType(MediaType.APPLICATION_JSON).content(technicalCardDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(technicalCardDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/technical_card/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/technical_card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
