package src.test.java.com.trade_accounting.controllers.rest.finance;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.finance.ReturnFromBuyersRestController;
import com.trade_accounting.models.dto.finance.ReturnFromBuyersDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/ReturnFromBuyers-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "zederon@mail.ru")
public class ReturnFromBuyersRestControllerTest {

    @Autowired
    private ReturnFromBuyersRestController returnFromBuyersRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(returnFromBuyersRestController, "ReturnFromBuyers Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/returnFromBuyers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getById() throws Exception {
        String returnFromBuyersDtoJson = new Gson().toJson(ReturnFromBuyersDto.builder()
                .id(1L)
                .date("12.02.2021 12:33")
                .warehouseId(1L)
                .contractorId(1L)
                .companyId(1L)
                .totalPrice(BigDecimal.valueOf(1000.00))
                .contractId(1L)
                .isSend(false)
                .isPrint(false)
                .comment("Возврат1")
                .isConducted(true)
                .build());

        mockMvc.perform(get("/api/returnFromBuyers/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(returnFromBuyersDtoJson));
    }

    @Test
    void create() throws Exception {
        String returnFromBuyersDtoJson = new Gson().toJson(ReturnFromBuyersDto.builder()
                .date("12.02.2021 12:35")
                .warehouseId(1L)
                .contractorId(1L)
                .companyId(1L)
                .totalPrice(BigDecimal.valueOf(5000.00))
                .contractId(1L)
                .isSend(true)
                .isPrint(true)
                .comment("Возврат3")
                .isConducted(true)
                .build()
        );

        mockMvc.perform(post("/api/returnFromBuyers")
                .contentType(MediaType.APPLICATION_JSON).content(returnFromBuyersDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(returnFromBuyersDtoJson));

        mockMvc.perform(get("/api/returnFromBuyers/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(returnFromBuyersDtoJson));
    }

    @Test
    void update() throws Exception {
        String returnFromBuyersDtoJson = new Gson().toJson(ReturnFromBuyersDto.builder()
                .id(2L)
                .date("12.02.2021 12:36")
                .warehouseId(1L)
                .contractorId(1L)
                .companyId(1L)
                .totalPrice(BigDecimal.valueOf(5000.00))
                .contractId(1L)
                .isSend(true)
                .isPrint(true)
                .comment("Возврат2")
                .isConducted(true)
                .build()
        );

        mockMvc.perform(put("/api/returnFromBuyers")
                .contentType(MediaType.APPLICATION_JSON).content(returnFromBuyersDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(returnFromBuyersDtoJson));

        mockMvc.perform(get("/api/returnFromBuyers/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(returnFromBuyersDtoJson));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/returnFromBuyers/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        mockMvc.perform(get("/api/returnFromBuyers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
