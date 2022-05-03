package src.test.java.com.trade_accounting.controllers.rest.company;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.company.ContractorRestController;
import com.trade_accounting.models.dto.company.ContractorDto;
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
@Sql(value = "/Contractor-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ContractorRestControllerTest {

    @Autowired
    private ContractorRestController contractorRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(contractorRestController, "Contractor Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/contractor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById () throws Exception {
        String modelJson = new Gson().toJson(ContractorDto.builder()
                .id(1L)
                .comment("comment")
                .commentToAddress("1 comment to address")
                .discountCardNumber("1234-5678-9012-3456")
                .email("alena.pechnikova@x5.ru")
                .fax("8 (495) 232-59-24")
                .name("Торговый Дом \"Перекресток\", ЗАО")
                .phone("8 (495) 232-59-24")
                .sortNumber("1")
                .accessParametersId(1L)
                .addressId(1L)
                .contractorGroupId(1L)
                .contractorStatusId(1L)
                .legalDetailId(1L)
                .typeOfPriceId(1L)
                .build());

        mockMvc.perform(get("/api/contractor/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate () throws Exception {
        List<Long> list = new ArrayList<>();
        String modelJson = new Gson().toJson(ContractorDto.builder()
                .id(1L)
                .comment("comment")
                .commentToAddress("1 comment to address")
                .discountCardNumber("1234-5678-9012-3456")
                .email("alena.pechnikova@x5.ru")
                .fax("8 (495) 232-59-24")
                .name("Торговый Дом \"Перекресток\", ЗАО")
                .phone("8 (495) 232-59-24")
                .sortNumber("1")
                .accessParametersId(1L)
                .addressId(1L)
                .contractorGroupId(1L)
                .contractorStatusId(1L)
                .legalDetailId(1L)
                .typeOfPriceId(1L)
                .bankAccountIds(list)
                .contactIds(list)
                .build());

        mockMvc.perform(post("/api/contractor")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contractor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testUpdate() throws Exception {
        List<Long> list = new ArrayList<>();
        String modelJson = new Gson().toJson(ContractorDto.builder()
                .id(1L)
                .comment("comment")
                .commentToAddress("1 comment to address")
                .discountCardNumber("1234-5678-9012-3456")
                .email("alena.pechnikova@x5.ru")
                .fax("8 (495) 232-59-24")
                .name("Торговый Дом \"Перекресток\", ЗАО")
                .phone("8 (495) 232-59-24")
                .sortNumber("1")
                .accessParametersId(1L)
                .addressId(1L)
                .contractorGroupId(1L)
                .contractorStatusId(1L)
                .legalDetailId(1L)
                .typeOfPriceId(1L)
                .bankAccountIds(list)
                .contactIds(list)
                .build());

        mockMvc.perform(put("/api/contractor")
                        .contentType(MediaType.APPLICATION_JSON).content(modelJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(modelJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contractor"))
                .andDo(print());
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/contractor/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contractor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
