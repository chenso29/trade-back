package src.test.java.com.trade_accounting.controllers.rest.warehouse;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.InventarizationRestController;
import com.trade_accounting.models.dto.warehouse.InventarizationDto;
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
@Sql(value = "/Inventarization-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class InventarizationRestControllerTest {

    @Autowired
    private InventarizationRestController inventarizationRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(inventarizationRestController, "Inventarization Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/inventarization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        InventarizationDto inventarizationDto = InventarizationDto.builder()
                .id(1L)
                .date("2021-07-03 12:25")
                .warehouseId(1L)
                .companyId(1L)
                .status(false)
                .comment("Инвентаризация 1")
                .inventarizationProductIds(List.of(1L))
                .build();

        String inventarizationDtoJson = new Gson().toJson(inventarizationDto);

        mockMvc.perform(get("/api/inventarization/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(inventarizationDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        InventarizationDto inventarizationDto = InventarizationDto.builder()
                .id(4L)
                .date("2021-07-03 12:25")
                .warehouseId(1L)
                .companyId(1L)
                .status(false)
                .comment("123213121")
                .inventarizationProductIds(List.of(1L))
                .build();

        String inventarizationDtoJson = new Gson().toJson(inventarizationDto);

        mockMvc.perform(post("/api/inventarization").contentType(MediaType.APPLICATION_JSON).content(inventarizationDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(inventarizationDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/inventarization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        InventarizationDto inventarizationDto = InventarizationDto.builder()
                .id(3L)
                .date("2021-07-03 12:25")
                .warehouseId(1L)
                .companyId(1L)
                .status(false)
                .comment("123213121")
                .inventarizationProductIds(List.of(1L))
                .build();

        String inventarizationDtoJson = new Gson().toJson(inventarizationDto);

        mockMvc.perform(put("/api/inventarization").contentType(MediaType.APPLICATION_JSON).content(inventarizationDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(inventarizationDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/inventarization"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/inventarization/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/inventarization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
