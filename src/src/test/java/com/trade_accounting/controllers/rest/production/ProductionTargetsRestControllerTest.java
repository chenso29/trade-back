package src.test.java.com.trade_accounting.controllers.rest.production;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.production.ProductionTargetsRestController;
import com.trade_accounting.models.dto.production.ProductionTargetsDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/prepayout-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ProductionTargetsRestControllerTest {

    @Autowired
    private ProductionTargetsRestController productionTargetsRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(productionTargetsRestController, "ProductionTargets Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/production_targets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(ProductionTargetsDto.builder()
                .id(1L)
                .date("2021-08-10 12:15")
                .companyId(1L)
                .deliveryPlannedMoment("2021-08-11 12:15")
                .materialWarehouse(Long.valueOf(1))
                .productionWarehouse(Long.valueOf(1))
                .productionStart("2021-08-09 08:00")
                .productionEnd("2021-08-22 08:00")
                .shared(false)
                .Owner("Гастроном")
                .employeeOwner("Гастрономский Городовой")
                .published(false)
                .printed(false)
                .description("комментарий 1")
                .updated("2021-08-22 08:00")
                .updatedByName("Я")
                .build());

        mockMvc.perform(get("/api/production_targets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        String modelJson = new Gson().toJson(ProductionTargetsDto.builder()
                .id(1L)
                .date("2021-08-10 12:15")
                .companyId(1L)
                .deliveryPlannedMoment("2021-08-11 12:15")
                .materialWarehouse(Long.valueOf(1))
                .productionWarehouse(Long.valueOf(1))
                .productionStart("2021-08-09 08:00")
                .productionEnd("2021-08-22 08:00")
                .shared(false)
                .Owner("Гастроном")
                .employeeOwner("Гастрономский Городовой")
                .published(false)
                .printed(false)
                .description("комментарий 1")
                .updated("2021-08-22 08:00")
                .updatedByName("Я")
                .build());

        mockMvc.perform(post("/api/production_targets")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/production_targets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdate() throws Exception {
        String modelJson = new Gson().toJson(ProductionTargetsDto.builder()
                .id(1L)
                .date("2021-08-10 12:15")
                .companyId(1L)
                .deliveryPlannedMoment("2021-08-11 12:15")
                .materialWarehouse(Long.valueOf(1))
                .productionWarehouse(Long.valueOf(1))
                .productionStart("2021-08-09 08:00")
                .productionEnd("2021-08-22 08:00")
                .shared(false)
                .Owner("Гастроном")
                .employeeOwner("Гастрономский Городовой")
                .published(false)
                .printed(false)
                .description("комментарий 1")
                .updated("2021-08-22 08:00")
                .updatedByName("Я")
                .build());

        mockMvc.perform(put("/api/production_targets")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/production_targets"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/production_targets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/production_targets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
