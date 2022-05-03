package src.test.java.com.trade_accounting.controllers.rest.util;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.util.BonusProgramRestController;
import com.trade_accounting.models.dto.util.BonusProgramDto;
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
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/BonusProgram-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class BonusProgramRestControllerTest {

    @Autowired
    BonusProgramRestController bonusProgramRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(bonusProgramRestController, "BonusProgram Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/bonusprogram"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        BonusProgramDto bonusProgramDto = BonusProgramDto.builder()
                .id(1L)
                .name("Бонусная программа 1")
                .activeStatus(true)
                .allContractors(false)
                .accrualRule(new BigDecimal(2.5))
                .writeOffRules(new BigDecimal(1.0))
                .maxPaymentPercentage((short)50)
                .numberOfDays((short)10)
                .welcomePoints(true)
                .numberOfPoints(10000L)
                .registrationInBonusProgram(true)
                .firstPurchase(false)
                .build();

        String bonusProgramDtoJson = new Gson().toJson(bonusProgramDto);

        mockMvc.perform(get("/api/bonusprogram/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(bonusProgramDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        BonusProgramDto bonusProgramDto = BonusProgramDto.builder()
                .id(6L)
                .name("Бонусная программа 1")
                .activeStatus(true)
                .allContractors(false)
                .accrualRule(new BigDecimal(2.5))
                .writeOffRules(new BigDecimal(1.0))
                .contractorGroupIds(List.of(1L,2L))
                .maxPaymentPercentage((short)50)
                .numberOfDays((short)10)
                .welcomePoints(true)
                .numberOfPoints(10000L)
                .registrationInBonusProgram(true)
                .firstPurchase(false)
                .build();

        String bonusProgramDtoJson = new Gson().toJson(bonusProgramDto);

        mockMvc.perform(post("/api/bonusprogram").contentType(MediaType.APPLICATION_JSON).content(bonusProgramDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(bonusProgramDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/bonusprogram"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void testUpdate() throws Exception {
        BonusProgramDto bonusProgramDto = BonusProgramDto.builder()
                .id(1L)
                .name("Бонусная программа update 1")
                .activeStatus(false)
                .allContractors(false)
                .accrualRule(new BigDecimal(2.5))
                .writeOffRules(new BigDecimal(1.0))
                .contractorGroupIds(List.of(1L,2L))
                .maxPaymentPercentage((short)50)
                .numberOfDays((short)10)
                .welcomePoints(false)
                .numberOfPoints(10000L)
                .registrationInBonusProgram(false)
                .firstPurchase(false)
                .build();

        String bonusProgramDtoJson = new Gson().toJson(bonusProgramDto);

        mockMvc.perform(put("/api/bonusprogram").contentType(MediaType.APPLICATION_JSON).content(bonusProgramDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(bonusProgramDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/bonusprogram"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/bonusprogram/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/bonusprogram"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
