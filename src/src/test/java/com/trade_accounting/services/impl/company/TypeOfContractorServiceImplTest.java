package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.TypeOfContractor;
import com.trade_accounting.models.dto.company.TypeOfContractorDto;
import com.trade_accounting.repositories.company.TypeOfContractorRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.company.TypeOfContractorDtoStubs;
import com.trade_accounting.utils.mapper.company.TypeOfContractorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypeOfContractorServiceImplTest {

    @Spy
    private TypeOfContractorMapper typeOfContractorMapper;

    @Mock
    private TypeOfContractorRepository typeOfContractorRepository;

    @InjectMocks
    private TypeOfContractorServiceImpl typeOfContractorService;

    @Test
    void getAll_shouldReturnListFilledTypeOfContractorDto() {
        when(typeOfContractorRepository.findAll())
                .thenReturn(Stream.of(
                        ModelStubs.getTypeOfContractor(1L),
                        ModelStubs.getTypeOfContractor(2L),
                        ModelStubs.getTypeOfContractor(3L)
                ).collect(Collectors.toList()));

        List<TypeOfContractorDto> typesOfContractors = typeOfContractorService.getAll();

        assertNotNull(typesOfContractors, "failure - expected that a list of typeOfContactorsDto not null");
        assertTrue(typesOfContractors.size() > 0, "failure - expected that a size of list of typeOfContactorsDto greater than 0");

        for (TypeOfContractorDto typeOfContractorDto : typesOfContractors) {
            typeOfContractorDtoIsCorrectlyInited(typeOfContractorDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListEmployeeDto() {
        when(typeOfContractorRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<TypeOfContractorDto> typesOfContractors = typeOfContractorService.getAll();

        assertNotNull(typesOfContractors, "failure - expected that a list of employeeDto not null");
        assertEquals(0, typesOfContractors.size(), "failure - expected that size of list of typeOfContractors equals 0");
    }

    @Test
    void getById_shouldReturnFilledTypeOfContractorDto() {
        Optional<TypeOfContractor> typeOfContractorRepo = Optional.of(ModelStubs.getTypeOfContractor(1L));

        when(typeOfContractorRepository.findById(anyLong()))
                .thenReturn(typeOfContractorRepo);

        TypeOfContractorDto typeOfContractor = typeOfContractorService.getById(1L);

        assertNotNull(typeOfContractor, "failure - expected that typeOfContractor not null.");
        typeOfContractorDtoIsCorrectlyInited(typeOfContractor);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        typeOfContractorService.create(TypeOfContractorDtoStubs.getTypeOfContractorDto(1L));

        verify(typeOfContractorRepository).save(any(TypeOfContractor.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        typeOfContractorService.update(TypeOfContractorDtoStubs.getTypeOfContractorDto(1L));

        verify(typeOfContractorRepository).save(any(TypeOfContractor.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        typeOfContractorService.deleteById(1L);
        verify(typeOfContractorRepository).deleteById(1L);
    }

    @Test
    void getByName_shouldReturnFilledTypeOfContractorDto() {
        Optional<TypeOfContractor> typeOfContractorRepo = Optional.of(ModelStubs.getTypeOfContractor(1L));

        when(typeOfContractorRepository.findByName(anyString()))
                .thenReturn(typeOfContractorRepo);

        TypeOfContractorDto typeOfContractor = typeOfContractorService.getByName("name");

        assertNotNull(typeOfContractor, "failure - expected that typeOfContractor not null.");
        typeOfContractorDtoIsCorrectlyInited(typeOfContractor);
    }

    @Test
    void getByName_shouldReturnEmptyTypeOfContractorDto() {
        Optional<TypeOfContractor> typeOfContractorRepo = Optional.empty();

        when(typeOfContractorRepository.findByName(anyString()))
                .thenReturn(typeOfContractorRepo);

        TypeOfContractorDto typeOfContractor = typeOfContractorService.getByName("name");

        assertEquals(new TypeOfContractorDto(), typeOfContractor, "failure - expected that typeOfContractor was empty");
    }


    void typeOfContractorDtoIsCorrectlyInited(TypeOfContractorDto typeOfContractorDto) {
        assertNotNull(typeOfContractorDto, "Fail in passed typeOfContractor");
        assertNotNull(typeOfContractorDto.getId(), "Fail in field 'id' of typeOfContractor");
        assertNotNull(typeOfContractorDto.getName(), "Fail in field 'name' of typeOfContractor");
        assertNotNull(typeOfContractorDto.getSortNumber(), "Fail in field 'sortNumber' of typeOfContractor");
    }
}