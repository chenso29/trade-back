package src.test.java.com.trade_accounting.controllers.rest.warehouse;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.warehouse.ShipmentProductRestController;
import com.trade_accounting.models.dto.warehouse.ShipmentDto;
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
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/shipments-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "vasyaogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ShipmentRestControllerTest {

    @Autowired
    private ShipmentProductRestController shipmentProductRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(shipmentProductRestController, "ProductionTargets Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/shipment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(ShipmentDto.builder()
                .id(1L)
                .date("2021-08-10T12:15:00")
                .warehouseId(1L)
                .contractorId(1L)
                .companyId(1L)
                .paid(BigDecimal.valueOf(1000))
                .isPrint(false)
                .isSend(true)
                .isSpend(true)
                .comment("comment one")
                .build());

        mockMvc.perform(get("/api/shipment/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        List<Long> list = new ArrayList<>();
        String modelJson = new Gson().toJson(ShipmentDto.builder()
                .id(1L)
                .date("2021-08-10T12:15:00")
                .warehouseId(1L)
                .contractorId(1L)
                .companyId(1L)
                .paid(BigDecimal.valueOf(1000))
                .isPrint(false)
                .isSend(true)
                .isSpend(true)
                .shipmentProductsIds(list)
                .comment("comment one")
                .build());

        mockMvc.perform(post("/api/shipment")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/shipment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdate() throws Exception {
        List<Long> list = new ArrayList<>();
        String modelJson = new Gson().toJson(ShipmentDto.builder()
                .id(1L)
                .date("2021-08-10T12:15:00")
                .warehouseId(1L)
                .contractorId(1L)
                .companyId(1L)
                .paid(BigDecimal.valueOf(1000))
                .isPrint(false)
                .isSend(true)
                .isSpend(true)
                .shipmentProductsIds(list)
                .comment("comment one")
                .build());

        mockMvc.perform(put("/api/shipment")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/shipment"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/shipment/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/shipment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
