package src.test.java.com.trade_accounting.controllers.rest.invoice;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.invoice.InvoicesStatusRestController;
import com.trade_accounting.models.dto.invoice.InvoicesStatusDto;
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
@Sql(value = "/InvoicesStatus-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class InvoicesStatusRestControllerTest {

    @Autowired
    InvoicesStatusRestController invoicesStatusRestController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(invoicesStatusRestController, "InvoicesStatusRestController is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/invoicestatus"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        String invoiceStatusDtoJson = new Gson().toJson(InvoicesStatusDto.builder()
                .id(1L)
                .statusName("Новый")
                .build());

        mockMvc.perform(get("/api/invoicestatus/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceStatusDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        String invoicesStatusDtoJson = new Gson().toJson(InvoicesStatusDto.builder()
                .id(5L)
                .statusName("Доставлен")
                .build());

        mockMvc.perform(post("/api/invoicestatus")
                        .contentType(MediaType.APPLICATION_JSON).content(invoicesStatusDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoicesStatusDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/invoicestatus"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void testUpdate() throws Exception {
        String invoicesStatusDtoJson = new Gson().toJson(InvoicesStatusDto.builder()
                .id(3L)
                .statusName("В пути")
                .build());

        mockMvc.perform(put("/api/invoicestatus")
                        .contentType(MediaType.APPLICATION_JSON).content(invoicesStatusDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoicesStatusDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/invoicestatus"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/invoicestatus/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/invoicestatus"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
