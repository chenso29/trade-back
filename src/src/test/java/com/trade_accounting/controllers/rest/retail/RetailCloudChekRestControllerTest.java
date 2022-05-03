package src.test.java.com.trade_accounting.controllers.rest.retail;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
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
@Sql(value = "/retailCloudCheck-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "vasyaogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class RetailCloudChekRestControllerTest {

    @Autowired
    private RetailCloudChekRestController retailCloudChekRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(retailCloudChekRestController, "ProductionTargets Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/cloudcheck"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(RetailCloudCheckDto.builder()
                .id(1L)
                .date("2021-09-19T09:03:00")
                .initiatorId(1L)
                .fiscalizationPointId(1L)
                .status("Хранение")
                .cheskStatus("Оплачен")
                .total(BigDecimal.valueOf(80000))
                .currencyId(1L)
                .cashierId(1L)
                .build());

        mockMvc.perform(get("/api/cloudcheck/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        String modelJson = new Gson().toJson(RetailCloudCheckDto.builder()
                .id(1L)
                .date("2021-09-19T09:03:00")
                .initiatorId(1L)
                .fiscalizationPointId(1L)
                .status("Хранение")
                .cheskStatus("Оплачен")
                .total(BigDecimal.valueOf(80000))
                .currencyId(1L)
                .cashierId(1L)
                .build());

        mockMvc.perform(post("/api/cloudcheck")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/cloudcheck"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdate() throws Exception {
        String modelJson = new Gson().toJson(RetailCloudCheckDto.builder()
                .id(1L)
                .date("2021-09-19T09:03:00")
                .initiatorId(1L)
                .fiscalizationPointId(1L)
                .status("Хранение")
                .cheskStatus("Оплачен")
                .total(BigDecimal.valueOf(80000))
                .currencyId(1L)
                .cashierId(1L)
                .build());

        mockMvc.perform(put("/api/cloudcheck")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/cloudcheck"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/cloudcheck/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/cloudcheck"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
