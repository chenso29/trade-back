package src.test.java.com.trade_accounting.controllers.rest.finance;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.finance.MutualSettlementsRestController;
import com.trade_accounting.models.dto.finance.MutualSettlementsDto;
import org.junit.jupiter.api.Assertions;
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
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/MutualSettlementsR-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class MutualSettlementsRestControllerTest {

    @Autowired
    private MutualSettlementsRestController mutualSettlementsRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(mutualSettlementsRestController, "MutualSettlements Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/mutualSettlements"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String mutualSettlementsDtoJson = new Gson().toJson(MutualSettlementsDto.builder()
                .id(1L)
                .finalBalance(28034)
                .initialBalance(45773)
                .income(39535)
                .expenses(93078)
                .contractorId(1L)
                .employeeId(2L));

        mockMvc.perform(get("/api/mutualSettlements/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(mutualSettlementsDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String mutualSettlementsDtoJson = new Gson().toJson(MutualSettlementsDto.builder()
                .finalBalance(33333)
                .initialBalance(77777)
                .income(44444)
                .expenses(55555)
                .contractorId(3L)
                .employeeId(2L)
                .build());

        mockMvc.perform(post("/api/mutualSettlements")
                .contentType(MediaType.APPLICATION_JSON).content(mutualSettlementsDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(mutualSettlementsDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/mutualSettlements"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));

    }

    @Test
    void update() throws Exception {
        String mutualSettlementsDtoJson = new Gson().toJson(MutualSettlementsDto.builder()
                .id(1L)
                .finalBalance(33333)
                .initialBalance(77777)
                .income(44444)
                .expenses(55555)
                .contractorId(3L)
                .employeeId(2L)
                .build());

        mockMvc.perform(put("/api/mutualSettlements")
                        .contentType(MediaType.APPLICATION_JSON).content(mutualSettlementsDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(mutualSettlementsDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/mutualSettlements/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/mutualSettlements"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
