package src.test.java.com.trade_accounting.controllers.rest.company;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.company.SupplierAccountRestController;
import com.trade_accounting.models.dto.company.SupplierAccountDto;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Sql(value = "/supplierAccounts-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class SupplierAccountRestControllerTest {

    @Autowired
    SupplierAccountRestController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testRestController() {
        assertNotNull(controller, "Controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/supplierAccount"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetById() throws Exception {
        String modelJson = new Gson().toJson(SupplierAccountDto.builder()
                .id(1L)
                .comment("Комментарий 1")
                .contractId(1L)
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2021-07-23 15:10")
                .isSpend(false)
                .build());
        mockMvc.perform(get("/api/supplierAccount/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testCreate() throws Exception {
        String modelJson = new Gson().toJson(SupplierAccountDto.builder()
                .id(4L)
                .comment("Комментарий 4")
                .contractId(1L)
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2021-07-23 15:10")
                .isSpend(true)
                .build());
        mockMvc.perform(post("/api/supplierAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/supplierAccount"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testUpdate() throws Exception {
        String modelJson = new Gson().toJson(SupplierAccountDto.builder()
                .id(2L)
                .comment("Комментарий 200")
                .contractId(1L)
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2021-07-23 15:10")
                .isSpend(false)
                .build());
        mockMvc.perform(put("/api/supplierAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/supplierAccount"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/supplierAccount/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/supplierAccount"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
