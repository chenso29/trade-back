package src.test.java.com.trade_accounting.controllers.rest.retail;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.retail.RetailShiftRestController;
import com.trade_accounting.models.dto.retail.RetailShiftDto;
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

import java.math.BigDecimal;

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
@Sql(value = "/retailShift-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "vasyaogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class RetailShiftRestControllerTest {

    @Autowired
    private RetailShiftRestController retailShiftRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(retailShiftRestController, "ProductionTargets Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/retailshift"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(RetailShiftDto.builder()
                .id(1L)
                .dataOpen("2021-08-10 12:15")
                .dataClose("2021-08-10 12:15")
                .retailStoreId(1L)
                .warehouseId(1L)
                .companyId(1L)
                .bank("банк 1")
                .revenuePerShift(BigDecimal.valueOf(1000.00))
                .received(BigDecimal.valueOf(1000.00))
                .amountOfDiscounts(BigDecimal.valueOf(1000.00))
                .commission_amount(BigDecimal.valueOf(1000.00))
                .sent(true)
                .printed(false)
                .comment("комент 1")
                .build());

        mockMvc.perform(get("/api/retailshift/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        String modelJson = new Gson().toJson(RetailShiftDto.builder()
                .id(1L)
                .dataOpen("2021-08-10 12:15")
                .dataClose("2021-08-10 12:15")
                .retailStoreId(1L)
                .warehouseId(1L)
                .companyId(1L)
                .bank("банк 1")
                .revenuePerShift(BigDecimal.valueOf(1000.00))
                .received(BigDecimal.valueOf(1000.00))
                .amountOfDiscounts(BigDecimal.valueOf(1000.00))
                .commission_amount(BigDecimal.valueOf(1000.00))
                .sent(true)
                .printed(false)
                .comment("комент 1")
                .build());

        mockMvc.perform(post("/api/retailshift")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/retailshift"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdate() throws Exception {
        String modelJson = new Gson().toJson(RetailShiftDto.builder()
                .id(1L)
                .dataOpen("2021-08-10 12:15")
                .dataClose("2021-08-10 12:15")
                .retailStoreId(1L)
                .warehouseId(1L)
                .companyId(1L)
                .bank("банк 1")
                .revenuePerShift(BigDecimal.valueOf(1000.00))
                .received(BigDecimal.valueOf(1000.00))
                .amountOfDiscounts(BigDecimal.valueOf(1000.00))
                .commission_amount(BigDecimal.valueOf(1000.00))
                .sent(true)
                .printed(false)
                .comment("комент 1")
                .build());

        mockMvc.perform(put("/api/retailshift")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/retailshift"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/retailshift/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/retailshift"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
