package src.test.java.com.trade_accounting.controllers.rest.warehouse;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.InventarizationProductRestController;
import com.trade_accounting.models.dto.warehouse.InventarizationProductDto;
import org.junit.jupiter.api.DisplayName;
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
@Sql(value = "/InventarizationProduct-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class InventarizationProductRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    InventarizationProductRestController inventarizationProductRestController;

    @Test
    void testExistence() {
        assertNotNull(inventarizationProductRestController, "inventarizationProductRestController is null");
    }

    @Test
    @DisplayName("Получаем все InventarizationProduct")
    void getAll() throws Exception {
        mockMvc.perform(get("/api/inventarization/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$",hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    @DisplayName("Получаем InventarizationProduct по id")
    void getById() throws Exception {
        InventarizationProductDto inventarizationProductDto = InventarizationProductDto.builder()
                .id(1L)
                .actualAmount(BigDecimal.valueOf(11))
                .price(BigDecimal.valueOf(111))
                .productId(1L)
                .build();

        String inventarizationProductDtoJson = new Gson().toJson(inventarizationProductDto);

        mockMvc.perform(get("/api/inventarization/product/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(inventarizationProductDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    @DisplayName("Создаем InventarizationProduct")
    void create() throws Exception {
        InventarizationProductDto inventarizationProductDto = InventarizationProductDto.builder()
                .id(4L)
                .actualAmount(BigDecimal.valueOf(44))
                .price(BigDecimal.valueOf(444))
                .productId(3L)
                .build();

        String inventarizationProductDtoJson = new Gson().toJson(inventarizationProductDto);

        mockMvc.perform(post("/api/inventarization/product")
                .contentType(MediaType.APPLICATION_JSON).content(inventarizationProductDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(inventarizationProductDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/inventarization/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    @DisplayName("Обновляем InventarizationProduct")
    void update() throws Exception {
        InventarizationProductDto inventarizationProductDto = InventarizationProductDto.builder()
                .id(3L)
                .actualAmount(BigDecimal.valueOf(33))
                .price(BigDecimal.valueOf(333))
                .productId(3L)
                .build();

        String inventarizationProductDtoJson = new Gson().toJson(inventarizationProductDto);

        mockMvc.perform(put("/api/inventarization/product")
                .contentType(MediaType.APPLICATION_JSON).content(inventarizationProductDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(inventarizationProductDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    @DisplayName("Удаляем InventarizationProduct")
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/inventarization/product/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/inventarization/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));

    }

}
