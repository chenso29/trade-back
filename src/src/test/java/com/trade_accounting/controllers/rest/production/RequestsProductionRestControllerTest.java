package src.test.java.com.trade_accounting.controllers.rest.production;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.production.RequestsProductionsRestController;
import com.trade_accounting.models.dto.production.RequestsProductionsDto;
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
@Sql(value = "/RequestsProductions-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class RequestsProductionRestControllerTest {

    private RequestsProductionsRestController requestsProductionsRestController;
    private MockMvc mockMvc;

    @Autowired
    public RequestsProductionRestControllerTest(RequestsProductionsRestController requestsProductionsRestController, MockMvc mockMvc) {
        this.requestsProductionsRestController = requestsProductionsRestController;
        this.mockMvc = mockMvc;
    }

    @Test
    void testExistence() {
        assertNotNull(requestsProductionsRestController, "Requests Productions Rest Controller is null");
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/processingorder"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String requestsProductionDtoJson = new Gson().toJson(RequestsProductionsDto.builder()
                .id(1L)
                .dateOfTheCertificate("2021-08-11")
                .numberOfTheCertificate("RP-001")
                .volume(500)
                .technicalCardId(1L)
                .warehouseId(1L)
                .build());

        mockMvc.perform(get("/api/processingorder/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(requestsProductionDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String requestsProductionDtoJson = new Gson().toJson(RequestsProductionsDto.builder()
                .id(4L)
                .dateOfTheCertificate("2021-08-11")
                .numberOfTheCertificate("RP-004")
                .volume(1500)
                .technicalCardId(2L)
                .warehouseId(2L)
                .build()
        );

        mockMvc.perform(post("/api/processingorder")
                .contentType(MediaType.APPLICATION_JSON).content(requestsProductionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(requestsProductionDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/processingorder"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));

    }
    @Test
    void update() throws Exception {
        String requestsProductionDtoJson = new Gson().toJson(RequestsProductionsDto.builder()
                .dateOfTheCertificate("2021-08-11")
                .numberOfTheCertificate("RP-004")
                .volume(2000)
                .technicalCardId(2L)
                .warehouseId(2L)
                .build()
        );

        mockMvc.perform(put("/api/processingorder")
                .contentType(MediaType.APPLICATION_JSON).content(requestsProductionDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(requestsProductionDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/processingorder"))
                .andDo(print());
    }
    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/processingorder/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/processingorder"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
