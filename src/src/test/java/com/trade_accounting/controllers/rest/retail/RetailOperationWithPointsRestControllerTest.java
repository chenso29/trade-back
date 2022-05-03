package src.test.java.com.trade_accounting.controllers.rest.retail;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.retail.RetailOperationWithPointsRestController;
import com.trade_accounting.models.dto.retail.RetailOperationWithPointsDto;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/RetailOperationWithPoints-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class RetailOperationWithPointsRestControllerTest {

    @Autowired
    RetailOperationWithPointsRestController restController;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void testExistence() {
        assertNotNull(restController, "RetailOperationWithPoints Rest Controller is null");
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/operation_with_points"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(5)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testGetById() throws Exception {
        RetailOperationWithPointsDto retailOperationWithPointsDto = RetailOperationWithPointsDto.builder()
                .id(1L)
                .number(1L)
                .currentTime("2021-08-10 12:15")
                .typeOperation("accrual")
                .numberOfPoints(1000L)
                .accrualDate("2021-08-10 12:15")
                .bonusProgramId(1L)
                .contractorId(1L)
                .taskId(1L)
                .fileIds(List.of(1L, 2L))
                .build();

        String retailOperationWithPointsDtoJson = new Gson().toJson(retailOperationWithPointsDto);
        mockMvc.perform(get("/api/operation_with_points/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailOperationWithPointsDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void testCreate() throws Exception {
        RetailOperationWithPointsDto retailOperationWithPointsDto = RetailOperationWithPointsDto.builder()
                .number(1L)
                .currentTime("2021-08-10 12:15")
                .typeOperation("accrual")
                .numberOfPoints(1000L)
                .accrualDate("2021-08-10 12:15")
                .bonusProgramId(1L)
                .contractorId(1L)
                .taskId(1L)
                .fileIds(List.of(1L, 2L))
                .build();

        String retailOperationWithPointsDtoJson = new Gson().toJson(retailOperationWithPointsDto);

        mockMvc.perform(post("/api/operation_with_points").contentType(MediaType.APPLICATION_JSON).content(retailOperationWithPointsDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailOperationWithPointsDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/operation_with_points"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void testUpdate() throws Exception {
        RetailOperationWithPointsDto retailOperationWithPointsDto = RetailOperationWithPointsDto.builder()
                .id(1L)
                .number(1L)
                .currentTime("2021-08-10 12:11")
                .typeOperation("начисление")
                .numberOfPoints(10L)
                .accrualDate("2021-08-10 12:11")
                .bonusProgramId(1L)
                .contractorId(1L)
                .taskId(1L)
                .fileIds(List.of(1L, 2L))
                .build();

        String retailOperationWithPointsDtoJson = new Gson().toJson(retailOperationWithPointsDto);

        mockMvc.perform(put("/api/operation_with_points").contentType(MediaType.APPLICATION_JSON).content(retailOperationWithPointsDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(retailOperationWithPointsDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/operation_with_points"))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/operation_with_points/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/operation_with_points"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
