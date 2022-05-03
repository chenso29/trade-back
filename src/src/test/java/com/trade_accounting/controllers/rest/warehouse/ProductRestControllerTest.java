package src.test.java.com.trade_accounting.controllers.rest.warehouse;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.ProductRestController;
import com.trade_accounting.models.dto.warehouse.ProductDto;
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
@Sql(value = "/Product-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ProductRestControllerTest {

    @Autowired
    private ProductRestController productRestController;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void testExistence() {
        assertNotNull(productRestController, "ProductRestController is null");
    }

    @SneakyThrows
    @Test
    void testGetAll() {
        mockMvc.perform(get("/api/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testGetById() {
        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("Яблоки0")
                .weight(BigDecimal.valueOf(1.00))
                .volume(BigDecimal.valueOf(1.00))
                .productGroupId(1L)
                .description("Красные яблоки голден0")
                .unitId(null)
                .archive(false)
                .service(false)
                .contractorId(null)
                .taxSystemId(null)
                .productGroupId(null)
                .attributeOfCalculationObjectId(null)
                .countryOrigin(null)
                .itemNumber(0)
                .saleTax(null)
                .minimumBalance(0)
                .build();

        String productDtoJson = new Gson().toJson(productDto);

        mockMvc.perform(get("/api/product/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testCreate() {
        ProductDto productDto = ProductDto.builder()
                .id(4L)
                .name("Яблоки4")
                .weight(BigDecimal.valueOf(4.00))
                .volume(BigDecimal.valueOf(4.00))
                .productGroupId(1L)
                .description("Красные яблоки голден")
                .unitId(1L)
                .archive(false)
                .service(false)
                .contractorId(1L)
                .taxSystemId(1L)
                .productGroupId(1L)
                .attributeOfCalculationObjectId(1L)
                .countryOrigin(null)
                .itemNumber(0)
                .saleTax(null)
                .minimumBalance(0)
                .build();

        String productDtoJson = new Gson().toJson(productDto);

        mockMvc.perform(post("/api/product").contentType(MediaType.APPLICATION_JSON)
                        .content(productDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @SneakyThrows
    @Test
    void testUpdate() {
        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("Яблоки4")
                .weight(BigDecimal.valueOf(4.00))
                .volume(BigDecimal.valueOf(4.00))
                .productGroupId(1L)
                .description("Красные яблоки голден")
                .unitId(1L)
                .archive(false)
                .service(false)
                .contractorId(1L)
                .taxSystemId(1L)
                .productGroupId(1L)
                .attributeOfCalculationObjectId(1L)
                .countryOrigin(null)
                .itemNumber(0)
                .saleTax(null)
                .minimumBalance(0)
                .build();

        String productDtoJson = new Gson().toJson(productDto);

        mockMvc.perform(put("/api/product").contentType(MediaType.APPLICATION_JSON)
                        .content(productDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/product"))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void testDelete() {
        mockMvc.perform(delete("/api/product/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
