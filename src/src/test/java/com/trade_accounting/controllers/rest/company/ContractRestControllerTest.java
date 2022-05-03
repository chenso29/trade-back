package src.test.java.com.trade_accounting.controllers.rest.company;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.company.ContractRestController;
import com.trade_accounting.models.dto.company.ContractDto;
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
@Sql(value = "/Contract-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class ContractRestControllerTest {

    @Autowired
    private ContractRestController contractRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(contractRestController, "Contract Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/contract"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String contractDtoJson = new Gson().toJson(ContractDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(200))
                .archive(false)
                .comment("no comments")
                .contractDate("2021-08-06")
                .number("1")
                .bankAccountId(3L)
                .companyId(1L)
                .contractorId(1L)
                .legalDetailId(1L)
                .build());

        mockMvc.perform(get("/api/contract/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contractDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String contractDtoJson = new Gson().toJson(ContractDto.builder()
                .number("21")
                .contractDate("2021-08-06")
                .companyId(1L)
                .bankAccountId(3L)
                .contractorId(1L)
                .amount(BigDecimal.valueOf(300))
                .archive(true)
                .comment("no comments")
                .legalDetailId(1L)
                .build()
        );

        mockMvc.perform(post("/api/contract")
                        .contentType(MediaType.APPLICATION_JSON).content(contractDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contractDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contract"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void update() throws Exception {
        String contractDtoJson = new Gson().toJson(ContractDto.builder()
                .id(3L)
                .amount(BigDecimal.valueOf(200))
                .archive(true)
                .comment("no commentssssss")
                .contractDate("2021-08-06")
                .number("21")
                .bankAccountId(3L)
                .companyId(1L)
                .contractorId(1L)
                .legalDetailId(1L)
                .build()
        );

        mockMvc.perform(put("/api/contract")
                        .contentType(MediaType.APPLICATION_JSON).content(contractDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contractDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/contract"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/contract/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/contract"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}