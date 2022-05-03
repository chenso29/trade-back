package src.test.java.com.trade_accounting.controllers.rest.retail;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.retail.RetailReturnRestController;
import com.trade_accounting.models.dto.retail.RetailReturnDto;
import com.trade_accounting.services.interfaces.retail.RetailReturnService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/RetailReturn-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class RetailReturnRestControllerTest {

    @Autowired
    RetailReturnRestController restController;

    @Autowired
    RetailReturnService retailReturnService;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(restController, "RetailReturn Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/returns"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        String retailReturnDtoJson = new Gson().toJson(RetailReturnDto.builder()
                .id(1L)
                .date("2021-09-20T15:08:35")
                .retailStoreId(1L)
                .cashAmount(new BigDecimal(1000))
                .cashlessAmount(new BigDecimal(5000))
                .isSpend(true)
                .isSend(true)
                .isPrint(false)
                .comment("comment one")
                .build()
        );

        mockMvc.perform(get("/api/returns/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailReturnDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        String retailReturnDtoJson = new Gson().toJson(RetailReturnDto.builder()
                .id(4L)
                .date("2021-09-20T15:08:35")
                .retailStoreId(1L)
                .cashAmount(new BigDecimal(1000))
                .cashlessAmount(new BigDecimal(5000))
                .isSpend(true)
                .isSend(true)
                .isPrint(false)
                .comment("comment one")
                .build()
        );

        mockMvc.perform(post("/api/returns").contentType(MediaType.APPLICATION_JSON).content(retailReturnDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailReturnDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/returns"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testUpdate() throws Exception {
        String retailReturnDtoJson = new Gson().toJson(RetailReturnDto.builder()
                .id(1L)
                .date("2021-09-20T15:08:35")
                .retailStoreId(1L)
                .cashAmount(new BigDecimal(11000))
                .cashlessAmount(new BigDecimal(15000))
                .isSpend(false)
                .isSend(false)
                .isPrint(false)
                .comment("comment one update")
                .build()
        );

        mockMvc.perform(put("/api/returns").contentType(MediaType.APPLICATION_JSON).content(retailReturnDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailReturnDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/returns"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/returns/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/returns"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
