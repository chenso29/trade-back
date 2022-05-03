package src.test.java.com.trade_accounting.controllers.rest.units;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.units.CurrencyRestController;
import com.trade_accounting.models.dto.units.CurrencyDto;
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
@Sql(value = "/Currency-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class CurrencyRestControllerTest {

    @Autowired
    private CurrencyRestController currencyRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(currencyRestController, "Currency Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/currency"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        CurrencyDto currencyDto = CurrencyDto.builder()
                .id(1L)
                .shortName("rubles")
                .fullName("Russian Rubles")
                .digitalCode("25")
                .letterCode("rub")
                .sortNumber("1")
                .build();

        String currencyDtoJson = new Gson().toJson(currencyDto);

        mockMvc.perform(get("/api/currency/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(currencyDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        CurrencyDto currencyDto = CurrencyDto.builder()
                .id(4L)
                .shortName("rubles")
                .fullName("Russian Rubles")
                .digitalCode("25")
                .letterCode("rub")
                .sortNumber("1")
                .build();

        String currencyDtoJson = new Gson().toJson(currencyDto);

        mockMvc.perform(post("/api/currency").contentType(MediaType.APPLICATION_JSON).content(currencyDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(currencyDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/currency"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        CurrencyDto currencyDto = CurrencyDto.builder()
                .id(3L)
                .shortName("rubles")
                .fullName("Russian Rubles")
                .digitalCode("25")
                .letterCode("rub")
                .sortNumber("1")
                .build();

        String currencyDtoJson = new Gson().toJson(currencyDto);

        mockMvc.perform(put("/api/currency").contentType(MediaType.APPLICATION_JSON).content(currencyDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(currencyDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/currency"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/currency/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/currency"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testGetAllSearch() throws Exception {
        mockMvc.perform(get("/api/currency/search"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
}
