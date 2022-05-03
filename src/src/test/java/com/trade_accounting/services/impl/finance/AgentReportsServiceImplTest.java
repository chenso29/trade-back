package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.AgentReports;
import com.trade_accounting.models.dto.finance.AgentReportsDto;
import com.trade_accounting.repositories.finance.AgentReportsRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.Stubs.dto.finance.AgentReportsDtoStubs;
import com.trade_accounting.Stubs.model.finance.AgentReportsModelStubs;
import com.trade_accounting.utils.mapper.finance.AgentReportsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgentReportsServiceImplTest {

    @InjectMocks
    private AgentReportsServiceImpl agentReportsService;

    @Mock
    private AgentReportsRepository agentReportsRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ContractorRepository contractorRepository;

    @Spy
    private AgentReportsMapper agentReportsMapper;

    @Test
    void repositoryIsNotNullTest() {
        assertNotNull(agentReportsRepository, "Repository is not filled");
    }

    @Test
    void getAllTest() {
        when(agentReportsRepository.findAll()).thenReturn(
                List.of(
                        AgentReportsModelStubs.getAgentReports(1L),
                        AgentReportsModelStubs.getAgentReports(2L),
                        AgentReportsModelStubs.getAgentReports(3L)
                )
        );
        List<AgentReportsDto> list = agentReportsService.getAll();
        assertEquals(3, list.size());
    }

    @Test
    void getByIdTest() {
        AgentReports reports = AgentReportsModelStubs.getAgentReports(1L);

        when(agentReportsRepository.getOne(anyLong()))
                .thenReturn(reports);
        AgentReportsDto dto = agentReportsService.getById(1L);

        assertEquals(1, dto.getId());
    }

    @Test
    void createTest() {
        saveOrUpdate();
    }

    @Test
    void updateTest() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        agentReportsService.deleteById(1L);
        verify(agentReportsRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(agentReportsRepository.save(any(AgentReports.class)))
                .thenReturn(AgentReportsModelStubs.getAgentReports(1L));

        AgentReportsDto stubs = AgentReportsDtoStubs.getDto(1L);

        AgentReportsDto agentReportsDto = agentReportsService
                .create(stubs);

        assertEquals(1, agentReportsDto.getId());
        verify(agentReportsRepository).save(any(AgentReports.class));
    }
}
