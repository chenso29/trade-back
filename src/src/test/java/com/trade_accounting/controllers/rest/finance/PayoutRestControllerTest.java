package src.test.java.com.trade_accounting.controllers.rest.finance;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.finance.PayoutRestController;
import com.trade_accounting.models.dto.finance.PayoutDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/Payout-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class PayoutRestControllerTest {

    @Autowired
    private PayoutRestController payoutRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
     void testExistence() throws Exception {
        assertNotNull(payoutRestController, "PayoutRestController is null");
    }

    @SneakyThrows
    @Test
     void getAllTest() {
        mockMvc.perform(get("/api/payout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void getAllByParametersTest() {
        mockMvc.perform(get("/api/payout/search/whoWho"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
            //    .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.length()").value(1))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
     void getById() {
        mockMvc.perform(get("/api/payout/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.whoWasPaid").value("whoWho"))
                .andExpect(jsonPath("$.isSent").value(false))
                .andExpect(jsonPath("$.comment").value("commentComment"))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
     void create() {
        PayoutDto payoutDto = PayoutDto.builder()
                .id(4L)
                .date("2021-07-20T14:11:40.3189726")
                .retailStoreId(1L)
                .whoWasPaid("test")
                .companyId(1L)
                .isPrint(true)
                .isSent(false)
                .comment("test")
                .build();

        String payoutDtoJson = new Gson().toJson(payoutDto);

        mockMvc.perform(post("/api/payout")
                .contentType(MediaType.APPLICATION_JSON).content(payoutDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(payoutDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/payout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @SneakyThrows
    @Test
     void update() {
        PayoutDto payoutDto = PayoutDto.builder()
                .id(2L)
                .date("2020-07-20T14:11:40.3189726")
                .retailStoreId(2L)
                .whoWasPaid("test1")
                .companyId(2L)
                .isPrint(true)
                .isSent(false)
                .comment("test1")
                .build();

        String payoutDtoJson = new Gson().toJson(payoutDto);

        mockMvc.perform(put("/api/payout")
                .contentType(MediaType.APPLICATION_JSON).content(payoutDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(payoutDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));


        mockMvc.perform(get("/api/payout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].whoWasPaid", containsInAnyOrder("who", "test1")))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @SneakyThrows
    @Test
     void deleteById() {
        mockMvc.perform(delete("/api/payout/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/payout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));

    }
}