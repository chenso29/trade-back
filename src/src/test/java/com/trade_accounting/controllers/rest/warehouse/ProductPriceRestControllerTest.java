package src.test.java.com.trade_accounting.controllers.rest.warehouse;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.ProductPriceRestController;
import com.trade_accounting.models.dto.warehouse.ProductPriceDto;
import lombok.SneakyThrows;
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
@Sql(value = "/ProductPrice-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ProductPriceRestControllerTest {

    @Autowired
    private ProductPriceRestController productPriceRestController;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void testExistence() {
        assertNotNull(productPriceRestController, "ProductPriceRestController is null");
    }

    @SneakyThrows
    @Test
    void testGetAll() {
        mockMvc.perform(get("/api/productprice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testGetById() {
        ProductPriceDto productPriceDto = ProductPriceDto.builder()
                .id(1L)
                .typeOfPriceId(null)
                .value(BigDecimal.valueOf(64.00))
                .build();

        String productPriceDtoJson = new Gson().toJson(productPriceDto);

        mockMvc.perform(get("/api/productprice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productPriceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testCreate() {
        ProductPriceDto productPriceDto = ProductPriceDto.builder()
                .id(4L)
                .typeOfPriceId(null)
                .value(BigDecimal.valueOf(64.00))
                .build();

        String productPriceDtoJson = new Gson().toJson(productPriceDto);

        mockMvc.perform(post("/api/productprice").contentType(MediaType.APPLICATION_JSON)
                        .content(productPriceDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productPriceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/productprice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @SneakyThrows
    @Test
    void testUpdate() {
        ProductPriceDto productPriceDto = ProductPriceDto.builder()
                .id(1L)
                .typeOfPriceId(null)
                .value(BigDecimal.valueOf(128.00))
                .build();

        String productPriceDtoJson = new Gson().toJson(productPriceDto);

        mockMvc.perform(put("/api/productprice").contentType(MediaType.APPLICATION_JSON)
                        .content(productPriceDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productPriceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/productprice"))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void testDelete() {
        mockMvc.perform(delete("/api/productprice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/productprice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
