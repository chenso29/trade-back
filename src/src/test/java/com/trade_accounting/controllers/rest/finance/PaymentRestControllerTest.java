package src.test.java.com.trade_accounting.controllers.rest.finance;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.finance.PaymentRestController;
import com.trade_accounting.models.dto.finance.PaymentDto;
import lombok.RequiredArgsConstructor;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/Payment-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@RequiredArgsConstructor
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class PaymentRestControllerTest {
    @Autowired
    private PaymentRestController paymentRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(paymentRestController, "Payment Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/payment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String paymentDtoJson = new Gson().toJson(PaymentDto.builder()
                .id(1L)
                .number("1")
                .paymentMethods("CASH")
                .sum(BigDecimal.valueOf(100))
                .time("2021-07-30 13:23:24")
                .typeOfPayment("INCOMING")
                .companyId(1L)
                .contractId(1L)
                .contractorId(2L)
                .projectId(2L));

        mockMvc.perform(get("/api/payment/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(paymentDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String paymentOrderDtoJson = new Gson().toJson(PaymentDto.builder()
                .number("2")
                .paymentMethods("BANK")
                .sum(BigDecimal.valueOf(30))
                .time("1234-12-12T12:34:20")
                .typeOfPayment("INCOMING")
                .expenseItem("RENTAL")
                .companyId(1L)
                .contractId(1L)
                .contractorId(2L)
                .projectId(2L)
                .build());

        mockMvc.perform(post("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON).content(paymentOrderDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(paymentOrderDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/payment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void update() throws Exception {
        String paymentOrderDtoJson = new Gson().toJson(PaymentDto.builder()
                .number("222")
                .paymentMethods("BANK")
                .sum(BigDecimal.valueOf(30))
                .time("1234-12-12T12:34:20")
                .typeOfPayment("INCOMING")
                .expenseItem("RENTAL")
                .companyId(1L)
                .contractId(1L)
                .contractorId(2L)
                .projectId(2L)
                .build());

        mockMvc.perform(put("/api/payment")
                        .contentType(MediaType.APPLICATION_JSON).content(paymentOrderDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(paymentOrderDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/payment"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/payment/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/payment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}