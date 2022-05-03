package src.test.java.com.trade_accounting.controllers.rest.finance;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.finance.AgentReportsRestController;
import com.trade_accounting.models.dto.finance.AgentReportsDto;
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
import static org.hamcrest.Matchers.hasToString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureMockMvc
@Sql(value = "/agentReports-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class AgentReportsRestControllerTest {

    @Autowired
    AgentReportsRestController agentReportsRestController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void controllerIsNotNullTest() {
        assertNotNull(agentReportsRestController, "rest controller is null");
    }

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(get("/api/agentReports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getByIdTest() throws Exception {
        mockMvc.perform(get("/api/agentReports/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$.id", hasToString("1")))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void createTest() throws Exception {
        String jsonDto = new Gson().toJson(AgentReportsDto.builder()
                .companyId(1L)
                .contractorId(1L)
                .comitentSum(1L)
                .commentary("Комментарий 1")
                .documentType(".doc")
                .number("1")
                .paid(1L)
                .printed(1L)
                .remunirationSum(1L)
                .sent(1L)
                .status("ok")
                .time("1234-12-12 12:34")
                .sum(1L)
                .build());

        mockMvc.perform(post("/api/agentReports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/agentReports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void updateTest() throws Exception {
        String jsonDto = new Gson().toJson(AgentReportsDto.builder()
                .id(1L)
                .companyId(1L)
                .contractorId(1L)
                .comitentSum(1L)
                .commentary("Комментарий 1111")
                .documentType(".doc")
                .number("1")
                .paid(1L)
                .printed(1L)
                .remunirationSum(1L)
                .sent(1L)
                .status("error")
                .time("1234-12-12 12:34")
                .sum(1L)
                .build());

        mockMvc.perform(put("/api/agentReports").contentType(MediaType.APPLICATION_JSON)
                .content(jsonDto))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$.status", hasToString("error")))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/agentReports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/api/agentReports/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/agentReports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
