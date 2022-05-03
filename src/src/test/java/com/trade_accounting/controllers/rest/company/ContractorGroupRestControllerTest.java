package src.test.java.com.trade_accounting.controllers.rest.company;

import com.google.gson.Gson;
import com.trade_accounting.models.dto.company.ContractorGroupDto;
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
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@Sql(value = "/ContractorGroup-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
class ContractorGroupRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/contractor/group"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(9)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void getById() throws Exception {
        String contractorGroupDtoJson = new Gson().toJson(ContractorGroupDto.builder()
                .id(1L)
                .name("Name 1")
                .sortNumber("1")
                .build()
        );

        mockMvc.perform(get("/api/contractor/group/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contractorGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    void create() throws Exception {
        String contractorGroupDtoJson = new Gson().toJson(ContractorGroupDto.builder()
                .name("Name 10")
                .sortNumber("10")
                .build()
        );

        mockMvc.perform(get("/api/contractor/group"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(9)));

        mockMvc.perform(post("/api/contractor/group")
                .contentType(MediaType.APPLICATION_JSON).content(contractorGroupDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contractorGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contractor/group/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contractorGroupDtoJson));

        mockMvc.perform(get("/api/contractor/group"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void update() throws Exception {
        String contractorGroupDtoJson = new Gson().toJson(ContractorGroupDto.builder()
                .id(5L)
                .name("Name 5 _UPDATED_")
                .sortNumber("5 _UPDATED_")
                .build()
        );

        mockMvc.perform(put("/api/contractor/group")
                .contentType(MediaType.APPLICATION_JSON).content(contractorGroupDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contractorGroupDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contractor/group/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contractorGroupDtoJson));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(get("/api/contractor/group/9"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated());

        mockMvc.perform(delete("/api/contractor/group/9"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contractor/group/9"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(authenticated());
    }
}