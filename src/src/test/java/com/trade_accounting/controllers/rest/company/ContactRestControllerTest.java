package src.test.java.com.trade_accounting.controllers.rest.company;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.company.ContactRestController;
import com.trade_accounting.models.dto.company.ContactDto;
import lombok.SneakyThrows;
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
@Sql(value = "/Contact-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ContactRestControllerTest {

    @Autowired
    private ContactRestController contactRestController;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void testExistence() {
        assertNotNull(contactRestController, "ContactRestController is null");
    }

    @SneakyThrows
    @Test
    void testGetAll() {
        mockMvc.perform(get("/api/contact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testGetById() {
        ContactDto contactDto = ContactDto.builder()
                .id(1L)
                .fullName("Иванов Иван Иванович")
                .position("Контактное лицо 1")
                .phone("+777-777-77-77")
                .email("email1@mail.ru")
                .comment("Коментарий 1")
                .build();

        String contactDtoJson = new Gson().toJson(contactDto);

        mockMvc.perform(get("/api/contact/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contactDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @SneakyThrows
    @Test
    void testCreate() {
        ContactDto contactDto = ContactDto.builder()
                .id(4L)
                .fullName("Леванов Лев Львович")
                .position("Контактное лицо 4")
                .phone("+444-444-44-44")
                .email("email4@mail.ru")
                .comment("Комментарий 4")
                .build();

        String contactDtoJson = new Gson().toJson(contactDto);

        mockMvc.perform(post("/api/contact").contentType(MediaType.APPLICATION_JSON)
                        .content(contactDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contactDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @SneakyThrows
    @Test
    void testUpdate() {
        ContactDto contactDto = ContactDto.builder()
                .id(3L)
                .fullName("Леванов Лев Львович")
                .position("Контактное лицо 4")
                .phone("+444-444-44-44")
                .email("email4@mail.ru")
                .comment("Комментарий 4")
                .build();

        String contactDtoJson = new Gson().toJson(contactDto);

        mockMvc.perform(put("/api/contact").contentType(MediaType.APPLICATION_JSON)
                        .content(contactDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(contactDtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contact"))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void testDelete() {
        mockMvc.perform(delete("/api/contact/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(get("/api/contact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
