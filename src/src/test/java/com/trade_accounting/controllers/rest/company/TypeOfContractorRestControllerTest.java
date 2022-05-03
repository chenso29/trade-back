package src.test.java.com.trade_accounting.controllers.rest.company;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.company.TypeOfContractorRestController;
import com.trade_accounting.models.dto.company.TypeOfContractorDto;
import lombok.SneakyThrows;
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

import static org.hamcrest.Matchers.containsInAnyOrder;
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
@Sql(value = "/TypeOfContractor-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "veraogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class TypeOfContractorRestControllerTest {

    @Autowired
    private TypeOfContractorRestController typeOfContractorControllerTest;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(typeOfContractorControllerTest, "TypeOfContractorRestControllerTest is null");
    }

    @SneakyThrows
    @Test
    public void testGetAll() {
        mockMvc.perform(get("/api/typeofcontractor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("TestType", "TypeTest")))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    public void testGetById() {
        mockMvc.perform(get("/api/typeofcontractor/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("TestType"))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    public void testCreate() {
        TypeOfContractorDto typeOfContractorDto = TypeOfContractorDto.builder()
                .id(4L)
                .name("type")
                .sortNumber("4")
                .build();

        String typeOfContractorDtoJson = new Gson().toJson(typeOfContractorDto);

        mockMvc.perform(post("/api/typeofcontractor")
                .contentType(MediaType.APPLICATION_JSON).content(typeOfContractorDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(typeOfContractorDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/typeofcontractor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @SneakyThrows
    @Test
    public void testUpdate() {
        TypeOfContractorDto typeOfContractorDto = TypeOfContractorDto.builder()
                .id(1L)
                .name("typeOfContracrot")
                .sortNumber("55")
                .build();

        String typeOfContractorDtoJson = new Gson().toJson(typeOfContractorDto);

        mockMvc.perform(put("/api/typeofcontractor")
                .contentType(MediaType.APPLICATION_JSON).content(typeOfContractorDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(typeOfContractorDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));


        mockMvc.perform(get("/api/typeofcontractor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("typeOfContracrot", "TypeTest")))
                .andExpect(jsonPath("$[*].sortNumber", containsInAnyOrder("55", "2")))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @SneakyThrows
    @Test
    public void testDeleteById() {
        mockMvc.perform(delete("/api/typeofcontractor/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/typeofcontractor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(1)));

    }
}
