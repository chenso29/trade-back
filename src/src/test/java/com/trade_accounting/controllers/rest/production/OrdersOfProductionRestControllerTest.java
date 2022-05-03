package src.test.java.com.trade_accounting.controllers.rest.production;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.production.OrdersOfProductionRestController;
import com.trade_accounting.models.dto.production.OrdersOfProductionDto;
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
@Sql(value = "/ordersOfProduction-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class OrdersOfProductionRestControllerTest {

    @Autowired
    private OrdersOfProductionRestController ordersOfProductionRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(ordersOfProductionRestController, "Contractor Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/orders_of_production"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    /*id, date, company_id, technical_card_id, volume, produce,
                                  planned_production_date, is_sent, is_print, comment*/
    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(OrdersOfProductionDto.builder()
                .id(1L)
                .date("2021-08-10 12:15")
                .companyId(1L)
                .technicalCardId(1L)
                .volume(120)
                .produce(0)
                .plannedProductionDate("2022-08-10 12:15")
                .isSent(false)
                .isPrint(true)
                .comment("Комментарий1")
                .build());

        mockMvc.perform(get("/api/orders_of_production/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        String modelJson = new Gson().toJson(OrdersOfProductionDto.builder()
                .id(1L)
                .date("2021-08-10 12:15")
                .companyId(1L)
                .technicalCardId(1L)
                .volume(120)
                .produce(0)
                .plannedProductionDate("2022-08-10 12:15")
                .isSent(false)
                .isPrint(true)
                .comment("Комментарий1")
                .build());

        mockMvc.perform(post("/api/orders_of_production")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/orders_of_production"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdate() throws Exception {
        String modelJson = new Gson().toJson(OrdersOfProductionDto.builder()
                .id(1L)
                .date("2021-08-10 12:15")
                .companyId(1L)
                .technicalCardId(1L)
                .volume(120)
                .produce(0)
                .plannedProductionDate("2022-08-10 12:15")
                .isSent(false)
                .isPrint(true)
                .comment("Комментарий1")
                .build());

        mockMvc.perform(put("/api/orders_of_production")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/orders_of_production"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/orders_of_production/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/orders_of_production"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
