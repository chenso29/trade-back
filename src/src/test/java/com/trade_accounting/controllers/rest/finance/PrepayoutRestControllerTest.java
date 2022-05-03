package src.test.java.com.trade_accounting.controllers.rest.finance;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.finance.PrepayoutRestController;
import com.trade_accounting.models.dto.finance.PrepayoutDto;
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
@Sql(value = "/prepayout-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class PrepayoutRestControllerTest {

    @Autowired
    private PrepayoutRestController prepayoutRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(prepayoutRestController, "Prepayout Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/prepayout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(PrepayoutDto.builder()
                .id(1L)
                .date("2021-08-11T12:00:00")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .cash(BigDecimal.valueOf(50000))
                .cashless(BigDecimal.valueOf(35000))
                .discount(BigDecimal.valueOf(5000))
                .sum(BigDecimal.valueOf(90000))
                .isSent(false)
                .isPrint(true)
                .comment("comment one")
                .build());

        mockMvc.perform(get("/api/prepayout/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        String modelJson = new Gson().toJson(PrepayoutDto.builder()
                .id(1L)
                .date("2021-08-11T12:00:00")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .cash(BigDecimal.valueOf(50000))
                .cashless(BigDecimal.valueOf(35000))
                .discount(BigDecimal.valueOf(5000))
                .sum(BigDecimal.valueOf(90000))
                .isSent(false)
                .isPrint(true)
                .comment("comment one")
                .build());

        mockMvc.perform(post("/api/prepayout")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/prepayout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdate() throws Exception {
        String modelJson = new Gson().toJson(PrepayoutDto.builder()
                .id(1L)
                .date("2021-08-11T12:00:00")
                .retailStoreId(1L)
                .contractorId(1L)
                .companyId(1L)
                .cash(BigDecimal.valueOf(50000))
                .cashless(BigDecimal.valueOf(35000))
                .discount(BigDecimal.valueOf(5000))
                .sum(BigDecimal.valueOf(90000))
                .isSent(false)
                .isPrint(true)
                .comment("comment one")
                .build());

        mockMvc.perform(put("/api/prepayout")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/prepayout"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/prepayout/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/prepayout"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
