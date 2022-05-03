package src.test.java.com.trade_accounting.controllers.rest.production;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.production.ProductionRestController;
import com.trade_accounting.models.dto.production.ProductionDto;
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
@Sql(value = "/Production-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ProductionRestControllerTest {

    ProductionRestController productionRestController;
    private MockMvc mockMvc;

    @Autowired
    public ProductionRestControllerTest(ProductionRestController productionRestController, MockMvc mockMvc) {
        this.productionRestController = productionRestController;
        this.mockMvc = mockMvc;
    }

    @Test
    void testExistence() {
        assertNotNull(productionRestController, "Production Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/production"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String productionDtoJson = new Gson().toJson(ProductionDto.builder()
                .id(1L)
                .technicalCardId(1L)
                .requestsProductionsId(1L)
                .build());

        mockMvc.perform(get("/api/production/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productionDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String productionDtoJson = new Gson().toJson(ProductionDto.builder()
                .id(null)
                .technicalCardId(2L)
                .requestsProductionsId(1L)
                .build()
        );

        mockMvc.perform(post("/api/production")
                .contentType(MediaType.APPLICATION_JSON).content(productionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productionDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/production"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));

    }
    @Test
    void update() throws Exception {
        String productionDtoJson = new Gson().toJson(ProductionDto.builder()
                .technicalCardId(3L)
                .requestsProductionsId(3L)
                .build()
        );

        mockMvc.perform(put("/api/production")
                .contentType(MediaType.APPLICATION_JSON).content(productionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productionDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/production"))
                .andDo(print());
    }
    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/production/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/production"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}