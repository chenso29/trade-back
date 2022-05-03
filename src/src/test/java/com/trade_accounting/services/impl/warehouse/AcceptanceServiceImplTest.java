package src.test.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.dto.warehouse.AcceptanceDto;
import com.trade_accounting.repositories.warehouse.AcceptanceRepository;
import com.trade_accounting.repositories.company.ContractRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.util.ProjectRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.Stubs.dto.warehouse.AcceptanceDtoStubs;
import com.trade_accounting.Stubs.model.warehouse.AcceptanceModelStubs;
import com.trade_accounting.utils.mapper.warehouse.AcceptanceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест сервиса Acceptance вкладки Приёмка")

@MockitoSettings(strictness = Strictness.LENIENT)
public class AcceptanceServiceImplTest {

    @Mock
    AcceptanceRepository acceptanceRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    ContractRepository contractRepository;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    WarehouseRepository warehouseRepository;

    @Spy
    AcceptanceMapper acceptanceMapper;

    @InjectMocks
    AcceptanceServiceImpl acceptanceService;

    @Test
    public void getAll() {
        when(acceptanceRepository.findAll()).thenReturn(
                List.of(AcceptanceModelStubs.getAcceptance(1L),
                        AcceptanceModelStubs.getAcceptance(2L),
                        AcceptanceModelStubs.getAcceptance(3L))
        );
        List<AcceptanceDto> acceptanceDtos = acceptanceService.getAll();
        assertEquals(3, acceptanceDtos.size());
    }

    @Test
    public void getById() {
        when(acceptanceRepository.getOne(anyLong()))
                .thenReturn(AcceptanceModelStubs.getAcceptance(1L));
        AcceptanceDto acceptanceDto = acceptanceService.getById(1L);

        AcceptanceDtoIsCorrectlyInited(acceptanceDto);
    }

    @Test
    public void create() {
        saveOrUpdate();
    }

    @Test
    public void update() {
        saveOrUpdate();
    }

    @Test
    public void deleteById() {
        acceptanceService.deleteById(anyLong());
        verify(acceptanceRepository).deleteById(anyLong());
    }


    private void saveOrUpdate() {
        when(acceptanceRepository.save(any(Acceptance.class))).thenReturn(AcceptanceModelStubs.getAcceptance(1L));
        AcceptanceDto acceptanceDto = acceptanceService.create(AcceptanceDtoStubs.getAcceptanceDto(1L));
        assertEquals(1, acceptanceDto.getId());
        verify(acceptanceRepository).save(any(Acceptance.class));
    }

    private void AcceptanceDtoIsCorrectlyInited(AcceptanceDto acceptanceDto) {
        assertNotNull(acceptanceDto, "failure - fail in passed acceptanceDto");
        assertNotNull(acceptanceDto.getId(), "failure - fail in field 'id' into acceptanceDto");
        assertNotNull(acceptanceDto.getAcceptanceProduction(), "failure - fail in field 'acceptanceProduction' into acceptanceDto");
        assertNotNull(acceptanceDto.getContractId(), "failure - fail in field 'contractId' into acceptanceDto");
        assertNotNull(acceptanceDto.getContractorId(), "failure - fail in field 'contractorId' into acceptanceDto");
        assertNotNull(acceptanceDto.getComment(), "failure - fail in field 'comment' into acceptanceDto");
        assertNotNull(acceptanceDto.getIncomingNumber(), "failure - fail in field 'incomingNumber' into acceptanceDto");
        assertNotNull(acceptanceDto.getDate(), "failure - fail in field 'date' into acceptanceDto");
        assertNotNull(acceptanceDto.getWarehouseId(), "failure - fail in field 'warehouseId' into acceptanceDto");
    }
}
