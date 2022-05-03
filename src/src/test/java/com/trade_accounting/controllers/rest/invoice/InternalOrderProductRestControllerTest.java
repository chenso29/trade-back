package src.test.java.com.trade_accounting.controllers.rest.invoice;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.invoice.InternalOrderProductRestController;
import com.trade_accounting.models.dto.invoice.InternalOrderProductsDto;
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
@Sql(value = "/InternalOrderProduct-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class InternalOrderProductRestControllerTest {
    @Autowired
    private InternalOrderProductRestController internalOrderProductRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(internalOrderProductRestController, "Internal Order Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/internalorder/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String internalOrderProductDtoJson = new Gson().toJson(InternalOrderProductsDto.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(2L))
                .price(BigDecimal.valueOf(3L))
                .productId(4L)
                .build());

        mockMvc.perform(get("/api/internalorder/product/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(internalOrderProductDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String internalOrderProductDtoJson = new Gson().toJson(InternalOrderProductsDto.builder()
                .amount(BigDecimal.valueOf(8L))
                .price(BigDecimal.valueOf(9L))
                .productId(9L)
                .build());

        mockMvc.perform(post("/api/internalorder/product")
                .contentType(MediaType.APPLICATION_JSON).content(internalOrderProductDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(internalOrderProductDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/internalorder/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void update() throws Exception {
        String internalOrderProductDtoJson = new Gson().toJson(InternalOrderProductsDto.builder()
                .id(5L)
                .amount(BigDecimal.valueOf(555L))
                .price(BigDecimal.valueOf(555L))
                .productId(6L)
                .build());

        mockMvc.perform(put("/api/internalorder/product")
                .contentType(MediaType.APPLICATION_JSON).content(internalOrderProductDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(internalOrderProductDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/internalorder/product/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(internalOrderProductDtoJson));

        mockMvc.perform(get("/api/internalorder/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/internalorder/product/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/internalorder/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}