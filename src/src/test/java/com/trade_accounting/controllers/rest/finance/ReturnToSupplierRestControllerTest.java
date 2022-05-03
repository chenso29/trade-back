package src.test.java.com.trade_accounting.controllers.rest.finance;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.finance.ReturnToSupplierRestController;
import com.trade_accounting.models.dto.finance.ReturnToSupplierDto;
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
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@WithUserDetails(value = "karimogon@mail.ru")
@Sql(value = "/ReturnSuppliers-before.sql")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ReturnToSupplierRestControllerTest {

    @Autowired
    ReturnToSupplierRestController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(controller, "ReturnToSupplierRestController Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/returnToSupplier"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetById() throws Exception {
        String returnToSupplierJson = new Gson().toJson(ReturnToSupplierDto.builder()
                .id(1L)
                .comment("Комментарий 1")
                .contractId(1L)
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2021-06-23 15:10")
                .isPrint(false)
                .isSend(false)
                .build());
        mockMvc.perform(get("/api/returnToSupplier/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(returnToSupplierJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testCreate() throws Exception {
        String returnToSupplierJson = new Gson().toJson(ReturnToSupplierDto.builder()
                .id(5L)
                .comment("Комментарий 5")
                .contractId(1L)
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2021-07-23 15:10")
                .isPrint(true)
                .isSend(true)
                .build());
        mockMvc.perform(post("/api/returnToSupplier").contentType(MediaType.APPLICATION_JSON)
                .content(returnToSupplierJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(returnToSupplierJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/returnToSupplier"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void testUpdate() throws Exception {
        String returnToSupplierJson = new Gson().toJson(ReturnToSupplierDto.builder()
                .id(2L)
                .comment("Комментарий 20")
                .contractId(1L)
                .companyId(1L)
                .contractorId(1L)
                .warehouseId(1L)
                .date("2021-07-23 15:10")
                .isPrint(true)
                .isSend(false)
                .build());
        mockMvc.perform(put("/api/returnToSupplier").contentType(MediaType.APPLICATION_JSON)
                .content(returnToSupplierJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(returnToSupplierJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/returnToSupplier"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/returnToSupplier/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/returnToSupplier"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

}
