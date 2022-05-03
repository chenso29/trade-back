package src.test.java.com.trade_accounting.controllers.rest.util;

import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.util.ImageRestController;
import com.trade_accounting.models.dto.util.ImageDto;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Andrey Melnikov
 * @since 11.08.2021
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.config.location = src/test/resources/application-test.yml"})
@WithMockUser(value = "karimogon@mail.ru")
@Sql(value = "/Image-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class ImageRestControllerTest {

    @Autowired
    private ImageRestController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExisting() {
        Assertions.assertNotNull(controller, "Error - ImageRestController is null");
    }

    @Test
    @SneakyThrows
    void getAll() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    @SneakyThrows
    void getById() {
        ImageDto imageDto = ImageDto.builder()
                .id(1L)
//                .fileExtension("image_url1") // тут чёрт ногу сломит)))
//                .content(new byte[]{}) // тут вторую
                .sortNumber("sort_number1")
                .build();

        String dtoJson = new Gson().toJson(imageDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/image/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    @SneakyThrows
    void create() {
        ImageDto imageDto = ImageDto.builder()
                .fileExtension("image_url1")
                .sortNumber("sort_number1")
                .build();

        String dtoJson = new Gson().toJson(imageDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/image")
                        .contentType(MediaType.APPLICATION_JSON).content(dtoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));
    }

    @Test
    @SneakyThrows
    void update() {
        ImageDto imageDto = ImageDto.builder()
                .id(1L)
                .fileExtension("UPDATED1")
                .sortNumber("UPDATED1")
                .build();

        String dtoJson = new Gson().toJson(imageDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/image")
                        .contentType(MediaType.APPLICATION_JSON).content(dtoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(dtoJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @SneakyThrows
    void deleteById() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/image/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/image"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }
}
