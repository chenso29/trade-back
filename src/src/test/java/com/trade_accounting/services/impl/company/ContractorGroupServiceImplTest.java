package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.ContractorGroup;
import com.trade_accounting.models.dto.company.ContractorGroupDto;
import com.trade_accounting.repositories.company.ContractorGroupRepository;
import com.trade_accounting.Stubs.dto.company.ContractorGroupDtoStubs;
import com.trade_accounting.utils.mapper.company.ContractorGroupMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractorGroupServiceImplTest {
    @Mock
    ContractorGroupRepository contractorGroupRepository;

    @Spy
    ContractorGroupMapperImpl contractorGroupMapper;

    @InjectMocks
    ContractorGroupServiceImpl contractorGroupService;

    @Test
    void getAllOnEmptyRepositoryShouldReturnEmptyList() {
        var contractorGroupDtos = contractorGroupService.getAll();
        assertNotNull(contractorGroupDtos, "ContractorGroupDtoList should not be null!");
        assertTrue(contractorGroupDtos.isEmpty(), "ContractorGroupDtoList should  be empty!");
    }

    @Test
    void getAllShouldReturnListOfContractorGroupDTO() {
        var contractorGroupDtos = contractorGroupDtos();
        when(contractorGroupRepository.getAll()).thenReturn(contractorGroupDtos);
        contractorGroupDtos = contractorGroupService.getAll();
        assertNotNull(contractorGroupDtos, "ContractorGroupDtoList should not be null!");
        assertFalse(contractorGroupDtos.isEmpty(), "ContractorGroupDtoList should not be empty!");
    }

    @Test
    void getByIdShouldReturnCorrectDto() {
        var dto = ContractorGroupDtoStubs.getContractorGroupDto(1L);
        when(contractorGroupRepository.getById(anyLong())).thenReturn(dto);
        var dtoFromRepository = contractorGroupService.getById(1L);
        assertNotNull(dtoFromRepository, "ContractorGroupDto should not be null!");
        assertEquals(dtoFromRepository, dto, "Returned dto must equal pre-filled");

    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        contractorGroupService.create(ContractorGroupDtoStubs.getContractorGroupDto(1L));
        verify(contractorGroupRepository).save(any(ContractorGroup.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        contractorGroupService.update(ContractorGroupDtoStubs.getContractorGroupDto(1L));
        verify(contractorGroupRepository).save(any(ContractorGroup.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        contractorGroupService.deleteById(1L);
        verify(contractorGroupRepository).deleteById(anyLong());
    }

    private List<ContractorGroupDto> contractorGroupDtos() {
        return LongStream.rangeClosed(1, 3).mapToObj(ContractorGroupDtoStubs::getContractorGroupDto)
                .collect(Collectors.toList());
    }
}