package src.test.java.com.trade_accounting.controllers.rest.invoice;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.invoice.IssuedInvoiceRestController;
import com.trade_accounting.models.dto.invoice.IssuedInvoiceDto;
import lombok.RequiredArgsConstructor;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/IssuedInvoice-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@RequiredArgsConstructor
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class IssuedInvoiceRestControllerTest {
    @Autowired
    private IssuedInvoiceRestController issuedInvoiceRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(issuedInvoiceRestController, "Invoice Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/issuedInvoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String invoiceDtoJson = new Gson().toJson(IssuedInvoiceDto.builder()
                .id(1L)
                .comment("comment 1")
                .date("2222-11-01T00:01:00")
                .isSpend(false)
                .companyId(1L)
                .contractorId(1L)
                .build());

        mockMvc.perform(get("/api/issuedInvoice/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String invoiceDtoJson = new Gson().toJson(IssuedInvoiceDto.builder()
                .id(2L)
                .comment("comment 2")
                .date("2222-11-01T00:01:00")
                .companyId(1L)
                .contractorId(1L)
                .isSpend(false)
                .build());
        mockMvc.perform(post("/api/issuedInvoice")
                        .contentType(MediaType.APPLICATION_JSON).content(invoiceDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/issuedInvoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void update() throws Exception {
        String invoiceDtoJson = new Gson().toJson(IssuedInvoiceDto.builder()
                .id(3L)
                .isSpend(false)
                .comment("comment 3")
                .date("2222-11-01T00:03:00")
                .companyId(1L)
                .contractorId(1L)
                .build());

        mockMvc.perform(put("/api/issuedInvoice")
                        .contentType(MediaType.APPLICATION_JSON).content(invoiceDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(invoiceDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/issuedInvoice"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/issuedInvoice/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/issuedInvoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
