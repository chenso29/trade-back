package src.test.java.com.trade_accounting.controllers.rest.units;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.units.UnitRestController;
import com.trade_accounting.models.dto.units.UnitDto;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/Unit-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class UnitRestControllerTest {

    @Autowired
    private UnitRestController unitRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(unitRestController, "UnitRestController is null");
    }

    @SneakyThrows
    @Test
    public void testGetAll() {
        mockMvc.perform(get("/api/unit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].shortName", containsInAnyOrder("Yard", "mm")))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    public void testGetById() {
        mockMvc.perform(get("/api/unit/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Millimetre"))
                .andExpect(jsonPath("$.sortNumber").value("2"))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    public void testCreate() {
        UnitDto unitDto = UnitDto.builder()
                .id(3L)
                .shortName("test")
                .fullName("testTest")
                .sortNumber("3")
                .build();

        String unitDtoJson = new Gson().toJson(unitDto);

        mockMvc.perform(post("/api/unit")
                .contentType(MediaType.APPLICATION_JSON).content(unitDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(unitDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/unit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @SneakyThrows
    @Test
    public void testUpdate() {
        UnitDto unitDto = UnitDto.builder()
                .id(1L)
                .shortName("testUpdate")
                .fullName("testTestUpdate")
                .sortNumber("22")
                .build();

        String unitDtoJson = new Gson().toJson(unitDto);

        mockMvc.perform(put("/api/unit")
                .contentType(MediaType.APPLICATION_JSON).content(unitDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(unitDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));


        mockMvc.perform(get("/api/unit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].fullName", containsInAnyOrder("testTestUpdate", "Millimetre")))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @SneakyThrows
    @Test
    public void testDeleteById() {
        mockMvc.perform(delete("/api/unit/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/unit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));

    }
}
