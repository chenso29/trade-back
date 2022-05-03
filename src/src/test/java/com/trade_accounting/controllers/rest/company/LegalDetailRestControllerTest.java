package src.test.java.com.trade_accounting.controllers.rest.company;


import com.google.gson.Gson;
import com.trade_accounting.controllers.rest.company.LegalDetailRestController;
import com.trade_accounting.models.dto.company.LegalDetailDto;
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

import java.time.LocalDate;

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
@Sql(value = "/legalDetail-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "karimogon@mail.ru")
@AutoConfigureRestDocs(outputDir = "target/snippets", uriScheme = "http", uriPort = 4444)
public class LegalDetailRestControllerTest {

    @Autowired
    private LegalDetailRestController legalDetailRestController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testExistence() throws Exception {
        assertNotNull(legalDetailRestController, "LegalDetailRestController is null");
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/legaldetail"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }


    @Test
    public void testGetById() throws Exception {
        String legalDetailJson = new Gson().toJson(LegalDetailDto.builder()
                .id(3L)
                .commentToAddress("comment to address")
                .dateOfTheCertificate(LocalDate.of(2018,06,06).toString())
                .firstName("Сергей")
                .inn("3661564265")
                .kpp("79196813")
                .lastName("Михайлов")
                .middleName("Антонович")
                .numberOfTheCertificate("432445")
                .ogrn("1051566516515")
                .okpo("70651656")
                .addressDtoId(1L)
                .dateOfTheCertificate("2018-06-06")
                .typeOfContractorDtoId(3L)
                .build());
        mockMvc.perform(get("/api/legaldetail/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(legalDetailJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testCreate() throws Exception {
        LegalDetailDto legalDetailDto = LegalDetailDto.builder()
                .id(4L)
                .commentToAddress("comment to address")
                // .dateOfTheCertificate(LocalDate.of(2018,06,06))
                .firstName("Андрей")
                .inn("3666454265")
                .kpp("79165813")
                .lastName("Андреев")
                .middleName("Андреевич")
                .numberOfTheCertificate("432845")
                .ogrn("1051513516515")
                .okpo("70151656")
                .addressDtoId(1L)
                .typeOfContractorDtoId(3L)
                .build();
        String createdLegalDetailJson = new Gson().toJson(legalDetailDto);
        mockMvc.perform(post("/api/legaldetail").contentType(MediaType.APPLICATION_JSON)
                .content(createdLegalDetailJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(createdLegalDetailJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/legaldetail"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(4)));
    }
//
    @Test
    public void testUpdate() throws Exception {
        LegalDetailDto updatedlegalDetail = LegalDetailDto.builder()
                .id(3L)
                .commentToAddress("comment to address")
                // .dateOfTheCertificate(LocalDate.of(2018,06,06))
                .firstName("Андрей")
                .inn("3666454265")
                .kpp("79165813")
                .lastName("Андреев")
                .middleName("Андреевич")
                .numberOfTheCertificate("432845")
                .ogrn("1051513516515")
                .okpo("70151656")
                .addressDtoId(2L)
                .typeOfContractorDtoId(2L)
                .build();
        String updatedLegaldetailJson = new Gson().toJson(updatedlegalDetail);
        mockMvc.perform(put("/api/legaldetail").contentType(MediaType.APPLICATION_JSON)
                .content(updatedLegaldetailJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(content().json(updatedLegaldetailJson))
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(delete("/api/legaldetail/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}"));
        mockMvc.perform(get("/api/legaldetail"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
