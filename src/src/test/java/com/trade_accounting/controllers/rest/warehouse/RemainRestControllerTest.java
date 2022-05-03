package src.test.java.com.trade_accounting.controllers.rest.warehouse;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.RemainRestController;
import com.trade_accounting.models.dto.warehouse.RemainDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/remain-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class RemainRestControllerTest {
    @Autowired
    private RemainRestController remainRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(remainRestController, "Remain Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/remain"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetById() throws Exception {
        String remainJson = new Gson().toJson(RemainDto.builder()
                .id(1L)
                .available(46973)
                .balance(23134)
                .costPrice(45773)
                .daysOnWarehouse(11)
                .expectation(28034)
                .irreducibleBalance(39535)
                .name("Remain one")
                .reserve(93078)
                .salesCost(56196)
                .salesSum(64145)
                .sumOfCostPrice(66039)
                .vendorCode("234789")
                .unitId(38L)
                .build()
        );
        mockMvc.perform(get("/api/remain/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(remainJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testCreate() throws Exception {
        String createdRemainJson = new Gson().toJson(RemainDto.builder()
                .id(4L)
                .available(1)
                .balance(1)
                .costPrice(1)
                .daysOnWarehouse(1)
                .expectation(1)
                .irreducibleBalance(1)
                .name("created")
                .reserve(1)
                .salesCost(1)
                .salesSum(1)
                .sumOfCostPrice(1)
                .vendorCode("created")
                .unitId(1L)
                .build()
        );
        mockMvc.perform(post("/api/remain").contentType(MediaType.APPLICATION_JSON)
                .content(createdRemainJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdRemainJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/remain"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        String updatedRemainJson = new Gson().toJson(RemainDto.builder()
                .id(3L)
                .available(1)
                .balance(1)
                .costPrice(1)
                .daysOnWarehouse(1)
                .expectation(1)
                .irreducibleBalance(1)
                .name("created")
                .reserve(1)
                .salesCost(1)
                .salesSum(1)
                .sumOfCostPrice(1)
                .vendorCode("created")
                .unitId(1L)
                .build()
        );
        mockMvc.perform(put("/api/remain").contentType(MediaType.APPLICATION_JSON)
                .content(updatedRemainJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedRemainJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/remain/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/remain"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
