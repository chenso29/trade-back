package src.test.java.com.trade_accounting.controllers.rest.warehouse;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.MovementRestController;
import com.trade_accounting.models.dto.warehouse.MovementDto;
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
@Sql(value = "/Movement-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class MovementRestControllerTest {

    @Autowired
    private MovementRestController movementRestController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(movementRestController, "Movement Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/movement"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        MovementDto movementDto = MovementDto.builder()
                .id(1L)
                .date("2021-07-16 15:10")
                .companyId(1L)
                .warehouseId(1L)
                .warehouseToId(2L)
                .isSent(false).isPrint(false)
                .comment("Перемещение 1")
                .movementProductsIds(List.of(1L, 2L, 3L)).build();

        String movementDtoJson = new Gson().toJson(movementDto);

        mockMvc.perform(get("/api/movement/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(movementDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        MovementDto movementDto = MovementDto.builder()
                .id(4L)
                .date("2021-07-16 15:10")
                .warehouseId(1L)
                .warehouseToId(2L)
                .companyId(3L)
                .isSent(false)
                .isPrint(false)
                .comment("Перемещение 4")
                .movementProductsIds(List.of(10L, 11L, 12L))
                .build();
        String movementDtoJson = new Gson().toJson(movementDto);
        mockMvc.perform(post("/api/movement")
                .contentType(MediaType.APPLICATION_JSON).content(movementDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(movementDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/movement"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        MovementDto movementDtoUpdate = MovementDto.builder()
                .id(3L)
                .date("2021-07-16 15:10")
                .warehouseId(2L)
                .warehouseToId(1L)
                .companyId(3L)
                .isSent(false)
                .isPrint(false)
                .comment("Перемещение 3 UPDATE")
                .movementProductsIds(List.of(7L, 8L, 9L))
                .build();
        String movementDtoJson = new Gson().toJson(movementDtoUpdate);
        mockMvc.perform(put("/api/movement")
                .contentType(MediaType.APPLICATION_JSON).content(movementDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(movementDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/movement"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/movement/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/movement"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
