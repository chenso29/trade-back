package src.test.java.com.trade_accounting.controllers.rest.company;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.company.PriceListRestController;
import com.trade_accounting.models.dto.company.PriceListDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Andrey Melnikov
 * @since 08.08.2021
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@WithUserDetails(value = "karimogon@mail.ru")
@Sql(value = "/PriceList-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@RequiredArgsConstructor
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class PriceListRestControllerTest {

    @Autowired
    private PriceListRestController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExisting() {
        Assertions.assertNotNull(controller, "Failure - controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/priceList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        PriceListDto priceListDto = PriceListDto.builder()
                .id(1L)
                .number("number1")
                .time("1234-12-12 12:34")
                .companyId(1L)
                .sent(false)
                .printed(false)
                .commentary("comment1")
                .build();

        String dtoJson = new Gson().toJson(priceListDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/priceList/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        PriceListDto priceListDto = PriceListDto.builder()
                .number("number1")
                .time("1234-12-12 12:34")
                .companyId(1L)
                .sent(false)
                .printed(false)
                .commentary("comment1")
                .build();

        String dtoJson = new Gson().toJson(priceListDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/priceList")
                        .contentType(MediaType.APPLICATION_JSON).content(dtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/priceList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));
    }

    @Test
    void update() throws Exception {
        PriceListDto priceListDto = PriceListDto.builder()
                .id(2L)
                .number("UPDATED")
                .time("1234-12-12 12:34")
                .companyId(2L)
                .sent(true)
                .printed(true)
                .commentary("UPDATED")
                .build();

        String dtoJson = new Gson().toJson(priceListDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/priceList")
                        .contentType(MediaType.APPLICATION_JSON).content(dtoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/priceList"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void deleteById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/priceList/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/priceList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }
}
