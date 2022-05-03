package src.test.java.com.trade_accounting.controllers.rest.company;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.company.BankAccountRestController;
import com.trade_accounting.models.dto.company.BankAccountDto;
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
@WithUserDetails(value = "karimogon@mail.ru")
@Sql(value = "/BankAccount-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class BankAccountRestControllerTest {

    @Autowired
    BankAccountRestController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(controller, "BankAccount Rest controller is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/bank/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetByBic() throws Exception{
        String bankAccountJson = new Gson().toJson(BankAccountDto.builder()
                .id(2L)
        .rcbic("rbic2")
        .bank("bank2")
        .address("address2")
        .correspondentAccount("correspondentAccount2")
        .account("account2")
        .mainAccount(true)
        .sortNumber("sortNumber2")
        .build());
        mockMvc.perform(get("/api/bank/account/bic/rbic2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(bankAccountJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testGetAllUniqueBic() throws Exception{
        mockMvc.perform(get("/api/bank/account/bic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }
    @Test
    public void testCreate() throws Exception{
        String bankAccountDto = new Gson().toJson(BankAccountDto.builder()
                .rcbic("rbic5")
                .bank("bank5")
                .address("address5")
                .correspondentAccount("correspondentAccount5")
                .account("account5")
                .mainAccount(true)
                .sortNumber("sortNumber5")
                .build());
        mockMvc.perform(post("/api/bank/account").contentType(MediaType.APPLICATION_JSON)
        .content(bankAccountDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(bankAccountDto))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/bank/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)));
    }
    @Test
    public void testUpdate() throws Exception{
        String bankAccountDto = new Gson().toJson(BankAccountDto.builder()
                .id(2L)
                .rcbic("rbic7")
                .bank("bank2")
                .address("address22")
                .correspondentAccount("correspondentAccount2")
                .account("account2sws")
                .mainAccount(true)
                .sortNumber("sortNumber2")
                .build());
        mockMvc.perform(put("/api/bank/account").contentType(MediaType.APPLICATION_JSON)
                .content(bankAccountDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(bankAccountDto))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/bank/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
    @Test
    public void testDelete()throws Exception{
        mockMvc.perform(delete("/api/bank/account/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/bank/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
