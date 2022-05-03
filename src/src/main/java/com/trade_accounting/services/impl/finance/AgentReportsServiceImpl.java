package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.AgentReports;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.dto.finance.AgentReportsDto;
import com.trade_accounting.repositories.finance.AgentReportsRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.services.interfaces.finance.AgentReportsService;
import com.trade_accounting.utils.mapper.finance.AgentReportsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AgentReportsServiceImpl implements AgentReportsService {

    private final AgentReportsRepository agentReportsRepository;
    private final CompanyRepository companyRepository;
    private final AgentReportsMapper agentReportsMapper;
    private final ContractorRepository contractorRepository;

    @Override
    public List<AgentReportsDto> getAll() {
        return agentReportsRepository.findAll().stream()
                .map(agentReportsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AgentReportsDto getById(Long id) {
        return agentReportsMapper.toDto(agentReportsRepository.getOne(id));
    }

    @Override
    public AgentReportsDto create(AgentReportsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public AgentReportsDto update(AgentReportsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        agentReportsRepository.deleteById(id);
    }

    private AgentReportsDto saveOrUpdate(AgentReportsDto dto) {
        AgentReports agentReports = agentReportsMapper.toModel(dto);

        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        Contractor contractor = contractorRepository.getContractorById(dto.getContractorId());
        LocalDateTime date = LocalDateTime.parse(dto.getTime().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        agentReports.setCompany(company);
        agentReports.setContractor(contractor);
        agentReports.setTime(date);

        return agentReportsMapper.toDto(agentReportsRepository.save(agentReports));
    }
}
