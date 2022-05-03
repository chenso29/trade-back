package src.test.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.StagesProduction;
import com.trade_accounting.models.dto.production.StagesProductionDto;
import com.trade_accounting.repositories.production.StagesProductionRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.production.StagesProductionDtoStubs;
import com.trade_accounting.utils.mapper.production.StagesProductionMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StagesProductionServiceImplTest {

    @InjectMocks
    private StagesProductionServiceImpl stagesProductionService;

    @Mock
    private  StagesProductionRepository stagesProductionRepository;

    @Spy
    private StagesProductionMapperImpl stagesProductionMapper;

    @Test
    void getAll_shouldReturnListFilledStagesProductionDto() {
        when(stagesProductionRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getStagesProduction(1L),
                                ModelStubs.getStagesProduction(2L),
                                ModelStubs.getStagesProduction(3L)
                        ).collect(Collectors.toList())
                );

        List<StagesProductionDto> stagesProductionDtos = stagesProductionService.getAll();

        assertNotNull(stagesProductionDtos, "Failure - expected that list of stagesProductionDtos not null");
        assertEquals(3, stagesProductionDtos.size(), "failure - expected that size of list of stagesProductionDtos equals 3");
        verify(stagesProductionRepository).findAll();

        for (StagesProductionDto stagesProductionDto : stagesProductionDtos) {
            stagesProductionDtoIsCorrectlyInited(stagesProductionDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListStagesProductionDto() {
        when(stagesProductionRepository.findAll())
                .thenReturn(
                        new ArrayList<>()
                );

        List<StagesProductionDto> stagesProductionDtos = stagesProductionService.getAll();

        assertNotNull(stagesProductionDtos, "Failure - expected that list of stagesProductionDtos not null");
        assertEquals(0, stagesProductionDtos.size(), "failure - expected that size of list of stagesProductionDtos equals 0");
    }

    @Test
    void getById_shouldReturnFilledStagesProductionDto() {
        when(stagesProductionRepository.getOne(anyLong()))
                .thenReturn(ModelStubs.getStagesProduction(1L));

        StagesProductionDto stagesProductionDto = stagesProductionService.getById(1L);

        stagesProductionDtoIsCorrectlyInited(stagesProductionDto);
    }

    @Test
    void search_shouldReturnListFilledStagesProductionDto() {
        when(stagesProductionRepository.findAll(Mockito.<Specification<StagesProduction>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getStagesProduction(1L),
                                ModelStubs.getStagesProduction(2L),
                                ModelStubs.getStagesProduction(3L)
                        ).collect(Collectors.toList())
                );

        List<StagesProductionDto> stagesProductionDtos = stagesProductionService
                .search(SpecificationStubs.getStagesProductionSpecificationStub());

        assertNotNull(stagesProductionDtos, "failure - expected that a list of stagesProductionDtos not null");
        assertTrue(stagesProductionDtos.size() > 0, "failure - expected that a list of stagesProductionDtos greater than 0");

        for (StagesProductionDto stagesProductionDto : stagesProductionDtos) {
            stagesProductionDtoIsCorrectlyInited(stagesProductionDto);
        }
    }

    @Test
    void search_shouldReturnEmptyListStagesProductionDto() {
        when(stagesProductionRepository.findAll(Mockito.<Specification<StagesProduction>>any()))
                .thenReturn(new ArrayList<>());

        List<StagesProductionDto> stagesProductionDtos = stagesProductionService
                .search(SpecificationStubs.getStagesProductionSpecificationStub());

        assertNotNull(stagesProductionDtos, "failure - expected that a list of stagesProductionDtos not null");
        assertEquals(0, stagesProductionDtos.size(), "failure - expected that size of list of stagesProductionDtos equals 0");
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        stagesProductionService.create(StagesProductionDtoStubs.getStagesProductionDto(1L));
        verify(stagesProductionRepository).save(any(StagesProduction.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        stagesProductionService.update(StagesProductionDtoStubs.getStagesProductionDto(1L));
        verify(stagesProductionRepository).save(any(StagesProduction.class));
    }

    @Test
    void deleteById_shouldPassInstructionSuccessfulDelete() {
        stagesProductionService.deleteById(1L);
        verify(stagesProductionRepository).deleteById(anyLong());
    }

    private void stagesProductionDtoIsCorrectlyInited(StagesProductionDto stagesProductionDto) {
        assertNotNull(stagesProductionDto, "failure - fail in passed stagesProductionDto");
        assertNotNull(stagesProductionDto.getId(), "failure - fail in field 'id' into stagesProductionDto");
        assertNotNull(stagesProductionDto.getName(), "failure - fail in field 'name' into stagesProductionDto");
        assertNotNull(stagesProductionDto.getDescription(), "failure - fail in field 'description' into stagesProductionDto");
        assertNotNull(stagesProductionDto.getDepartmentId(), "failure - fail in field 'departmentId' into stagesProductionDto");
        assertNotNull(stagesProductionDto.getEmployeeId(), "failure - fail in field 'employeeId' into stagesProductionDto");
    }
}
