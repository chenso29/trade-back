package src.test.java.com.trade_accounting.controllers.rest.warehouse;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.BuyersReturnRestController;
import com.trade_accounting.models.dto.warehouse.BuyersReturnDto;
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
@Sql(value = "/BuyersReturn-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class BuyersReturnRestControllerTest {

    @Autowired
    private BuyersReturnRestController buyersReturnRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(buyersReturnRestController, "Buyers Return  Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/buyersreturn"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(BuyersReturnDto.builder()
                .id(1L)
                .comment("comment one")
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2222-11-01T00:01:00")
                .sum(BigDecimal.valueOf(50000))
                .isPrint(true)
                .isSent(false)
                .build());

        mockMvc.perform(get("/api/buyersreturn/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        String modelJson = new Gson().toJson(BuyersReturnDto.builder()
                .id(2L)
                .comment("comment one")
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2222-11-01T00:02:00")
                .sum(BigDecimal.valueOf(47700))
                .isPrint(false)
                .isSent(false)
                .build());
        mockMvc.perform(post("/api/buyersreturn")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/buyersreturn"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testUpdate() throws Exception {
        String modelJson = new Gson().toJson(BuyersReturnDto.builder()
                .id(3L)
                .comment("comment one")
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2222-11-01T00:03:00")
                .sum(BigDecimal.valueOf(935000))
                .isPrint(true)
                .isSent(true)
                .build());

        mockMvc.perform(put("/api/buyersreturn")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/buyersreturn"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/buyersreturn/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/buyersreturn"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
