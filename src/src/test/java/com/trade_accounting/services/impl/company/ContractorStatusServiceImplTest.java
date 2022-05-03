package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.ContractorStatus;
import com.trade_accounting.models.dto.company.ContractorStatusDto;
import com.trade_accounting.repositories.company.ContractorStatusRepository;
import com.trade_accounting.Stubs.dto.company.ContractorStatusDtoStubs;
import com.trade_accounting.Stubs.model.company.ContractorStatusModelStubs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractorStatusServiceImplTest {

    @Mock
    private ContractorStatusRepository contractorStatusRepository;

    @InjectMocks
    private ContractorStatusServiceImpl contractorStatusService;

    @Test
    void getAll(){
        when(contractorStatusRepository.findAll())
                .thenReturn(List.of(
                        ContractorStatusModelStubs.getContractorStatus(1L),
                        ContractorStatusModelStubs.getContractorStatus(2L),
                        ContractorStatusModelStubs.getContractorStatus(3L)
                ));
        List<ContractorStatusDto> contractorStatusDtos = contractorStatusService.getAll();

        assertEquals(3,contractorStatusDtos.size());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void getById() {
        when(contractorStatusRepository.getOne(anyLong()))
                .thenReturn(ContractorStatusModelStubs.getContractorStatus(1L));

        ContractorStatusDto contractorStatusDto = contractorStatusService.getById(1L);

        assertEquals(1, contractorStatusDto.getId());
    }

    @Test
    void deleteById() {
        contractorStatusService.deleteById(anyLong());
        verify(contractorStatusRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(contractorStatusRepository.save(any(ContractorStatus.class)))
                .thenReturn(ContractorStatusModelStubs.getContractorStatus(1L));

        ContractorStatusDto contractorStatusDto = contractorStatusService
                .create(ContractorStatusDtoStubs.getDto(1L));

        assertEquals(1, contractorStatusDto.getId());
        verify(contractorStatusRepository).save(any(ContractorStatus.class));
    }
}
