package src.test.java.com.trade_accounting.controllers.rest.purchases;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.purchases.PurchaseControlRestController;
import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
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
@Sql(value = "/PurchaseControl-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class PurchaseControlRestControllerTest {

    @Autowired
    private PurchaseControlRestController purchaseControlRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(purchaseControlRestController, "Purchase Control Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/purchasecontrol"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String purchaseControlDtoJson = new Gson().toJson(PurchaseControlDto.builder()
                        .id(1L)
                        .articleNumber(1L)
                        .productCode(1L)
                        .productMeasure("quantity")
                        //.productName("skirt")
                        .productQuantity(10000L)
                        .currentBalanceId(1L)
                        .forecastId(1L)
                        .historyOfSalesId(1L)
                        .build());

        mockMvc.perform(get("/api/purchasecontrol/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(purchaseControlDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String purchaseControlDtoJson = new Gson().toJson(PurchaseControlDto.builder()
                .id(4L)
                .articleNumber(1L)
                .productCode(1L)
                .productMeasure("quantity")
                //.productName("skirt")
                .productQuantity(10000L)
                .currentBalanceId(1L)
                .forecastId(1L)
                .historyOfSalesId(1L)
                .build()
        );

        mockMvc.perform(post("/api/purchasecontrol")
                .contentType(MediaType.APPLICATION_JSON).content(purchaseControlDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(purchaseControlDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/purchasecontrol"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void update() throws Exception {
        String purchaseControlDtoJson = new Gson().toJson(PurchaseControlDto.builder()
                .id(1L)
                .articleNumber(2L)
                .productCode(2L)
                .productMeasure("quantity")
                //.productName("skirt")
                .productQuantity(10000L)
                .currentBalanceId(2L)
                .forecastId(2L)
                .historyOfSalesId(2L)
                .build()
        );

        mockMvc.perform(put("/api/purchasecontrol")
                .contentType(MediaType.APPLICATION_JSON).content(purchaseControlDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(purchaseControlDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/purchasecontrol"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/purchasecontrol/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
}