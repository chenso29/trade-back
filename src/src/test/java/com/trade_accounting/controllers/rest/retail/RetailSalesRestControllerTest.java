package src.test.java.com.trade_accounting.controllers.rest.retail;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.retail.RetailSalesRestController;
import com.trade_accounting.models.dto.retail.RetailSalesDto;
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
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/RetailSales-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class RetailSalesRestControllerTest {

    @Autowired
    RetailSalesRestController retailSalesRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(retailSalesRestController, "RetailSales Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/retail_sales"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        System.out.println(LocalDate.of(2021, 06, 01));
        RetailSalesDto retailSalesDto = RetailSalesDto.builder()
                .id(1L)
                .time("2021-08-11")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .sumCash(BigDecimal.valueOf(145000.00))
                .sumNonСash(BigDecimal.valueOf(0.00))
                .prepayment(BigDecimal.valueOf(0.00))
                .sumDiscount(BigDecimal.valueOf(0.00))
                .sum(BigDecimal.valueOf(145000.00))
                .sent(false)
                .printed(false)
                .comment("Комментарий 1")
                .build();

        String retailSalesDtoJson = new Gson().toJson(retailSalesDto);

        mockMvc.perform(get("/api/retail_sales/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailSalesDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        RetailSalesDto retailSalesDto = RetailSalesDto.builder()
                .id(6L)
                .time("2021-08-11")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .sumCash(BigDecimal.valueOf(145000.00))
                .sumNonСash(BigDecimal.valueOf(0.00))
                .prepayment(BigDecimal.valueOf(0.00))
                .sumDiscount(BigDecimal.valueOf(0.00))
                .sum(BigDecimal.valueOf(145000.00))
                .sent(false)
                .printed(false)
                .comment("Комментарий 1")
                .build();

        String retailSalesDtoJson = new Gson().toJson(retailSalesDto);

        mockMvc.perform(post("/api/retail_sales").contentType(MediaType.APPLICATION_JSON)
                .content(retailSalesDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailSalesDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/retail_sales"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void testUpdate() throws Exception {
        RetailSalesDto retailSalesDto = RetailSalesDto.builder()
                .id(5L)
                .time("2021-08-11")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .sumCash(BigDecimal.valueOf(145000.00))
                .sumNonСash(BigDecimal.valueOf(0.00))
                .prepayment(BigDecimal.valueOf(0.00))
                .sumDiscount(BigDecimal.valueOf(10000000.00))
                .sum(BigDecimal.valueOf(145000.00))
                .sent(false)
                .printed(false)
                .comment("Комментарий 77")
                .build();

        String retailStoreDtoJson = new Gson().toJson(retailSalesDto);

        mockMvc.perform(put("/api/retail_sales").contentType(MediaType.APPLICATION_JSON)
                .content(retailStoreDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailStoreDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/retail_sales"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/retail_sales/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/retail_sales"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
