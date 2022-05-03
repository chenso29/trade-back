package src.test.java.com.trade_accounting.controllers.rest.warehouse;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.ProductGroupRestController;
import com.trade_accounting.models.dto.warehouse.ProductGroupDto;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/ProductGroup-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ProductGroupRestControllerTest {

    @Autowired
    private ProductGroupRestController productGroupRestController;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void testExistence() {
        assertNotNull(productGroupRestController, "ProductGroupRestController is null");
    }

    @SneakyThrows
    @Test
    void testGetAll() {
        mockMvc.perform(get("/api/productgroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testGetById() {
        ProductGroupDto productGroupDto = ProductGroupDto.builder()
                .id(1L)
                .name("Товарная группа №1")
                .sortNumber("1")
                .serviceGroup(false)
//                .parentId(null)
                .build();

        String productGroupDtoJson = new Gson().toJson(productGroupDto);

        mockMvc.perform(get("/api/productgroup/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testCreate() {
        ProductGroupDto productGroupDto = ProductGroupDto.builder()
                .id(4L)
                .name("Товарная группа №4")
                .sortNumber("4")
                .serviceGroup(false)
                .parentId(1L)
                .build();

        String productGroupDtoJson = new Gson().toJson(productGroupDto);

        mockMvc.perform(post("/api/productgroup").contentType(MediaType.APPLICATION_JSON)
                        .content(productGroupDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/productgroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @SneakyThrows
    @Test
    void testUpdate() {
        ProductGroupDto productGroupDto = ProductGroupDto.builder()
                .id(3L)
                .name("Товарная группа №6")
                .sortNumber("2")
                .serviceGroup(false)
                .parentId(1L)
                .build();

        String productGroupDtoJson = new Gson().toJson(productGroupDto);

        mockMvc.perform(put("/api/productgroup").contentType(MediaType.APPLICATION_JSON)
                        .content(productGroupDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(productGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/productgroup"))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void testDelete() {
        mockMvc.perform(delete("/api/productgroup/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/productgroup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
