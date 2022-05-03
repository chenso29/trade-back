package src.test.java.com.trade_accounting.controllers.rest.finance;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.finance.PrepaymentReturnRestController;
import com.trade_accounting.models.dto.finance.PrepaymentReturnDto;
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
@Sql(value = "/prepaymentReturns-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class PrepaymentReturnRestControllerTest {

    @Autowired
    private PrepaymentReturnRestController prepaymentReturnRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(prepaymentReturnRestController, "PrepaymentReturns Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/prepaymentreturn"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

  /*id, time, retail_store_id, contractor_id, company_id, sum_cash,
                                sum_non_cash, sent, printed, comment*/
    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(PrepaymentReturnDto.builder()
                .id(1L)
                .time("2021-08-11")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .sumCash(BigDecimal.valueOf(88000.00))
                .sumNonСash(BigDecimal.valueOf(0.00))
                .sent(false)
                .printed(false)
                .comment("Комментарий 1")
                .build());

        mockMvc.perform(get("/api/prepaymentreturn/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        String modelJson = new Gson().toJson(PrepaymentReturnDto.builder()
                .id(1L)
                .time("2021-08-11")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .sumCash(BigDecimal.valueOf(88000.00))
                .sumNonСash(BigDecimal.valueOf(0.00))
                .sent(false)
                .printed(false)
                .comment("Комментарий 1")
                .build());

        mockMvc.perform(post("/api/prepaymentreturn")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/prepaymentreturn"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdate() throws Exception {
        String modelJson = new Gson().toJson(PrepaymentReturnDto.builder()
                .id(1L)
                .time("2021-08-11")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .sumCash(BigDecimal.valueOf(88000.00))
                .sumNonСash(BigDecimal.valueOf(0.00))
                .sent(false)
                .printed(false)
                .comment("Комментарий 1")
                .build());

        mockMvc.perform(put("/api/prepaymentreturn")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/prepaymentreturn"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/prepaymentreturn/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/prepaymentreturn"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
