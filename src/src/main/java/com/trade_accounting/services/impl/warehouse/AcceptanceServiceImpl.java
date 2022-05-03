package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.dto.warehouse.AcceptanceDto;
import com.trade_accounting.repositories.warehouse.AcceptanceRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.util.ProjectRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.warehouse.AcceptanceService;
import com.trade_accounting.utils.mapper.warehouse.AcceptanceMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptanceServiceImpl implements AcceptanceService {

    private final AcceptanceRepository acceptanceRepository;

    private final ContractRepository contractRepository;

    private final ContractorRepository contractorRepository;

    private final CompanyRepository companyRepository;

    private final ProjectRepository projectRepository;

    private final WarehouseRepository warehouseRepository;

    private final AcceptanceMapper acceptanceMapper;

    public AcceptanceServiceImpl(AcceptanceRepository acceptanceRepository,
                                 ContractRepository contractRepository,
                                 ContractorRepository contractorRepository,
                                 CompanyRepository companyRepository, ProjectRepository projectRepository,
                                 WarehouseRepository warehouseRepository,
                                 AcceptanceMapper acceptanceMapper) {
        this.acceptanceRepository = acceptanceRepository;
        this.contractRepository = contractRepository;
        this.contractorRepository = contractorRepository;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
        this.warehouseRepository = warehouseRepository;
        this.acceptanceMapper = acceptanceMapper;
    }

    @Override
    public List<AcceptanceDto> getAll() {
        List<Acceptance> acceptanceList = acceptanceRepository.findAll();
        return acceptanceList.stream().map(acceptanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AcceptanceDto getById(Long id) {
        return acceptanceMapper.toDto(acceptanceRepository.getOne(id));
    }

    @Override
    public AcceptanceDto create(AcceptanceDto dto) {

        Acceptance acceptance = acceptanceRepository.save(acceptanceMapper.toModel(dto));
        dto.setId(acceptance.getId());
        return dto;
    }

    @Override
    public AcceptanceDto update(AcceptanceDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        acceptanceRepository.deleteById(id);
    }

    @Override
    public List<AcceptanceDto> searchByNumberAndComment(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            return acceptanceRepository.findAll().stream()
                    .map(acceptanceMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return acceptanceRepository.search(searchTerm).stream()
                    .map(acceptanceMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<AcceptanceDto> search(String search) {
        return acceptanceRepository.search(search).stream()
                .map(acceptanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void moveToRecyclebin(long id) {
        Acceptance acceptance = acceptanceRepository.getOne(id);
        acceptance.setIsRecyclebin(true);
        acceptanceRepository.save(acceptance);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        Acceptance acceptance = acceptanceRepository.getOne(id);
        acceptance.setIsRecyclebin(false);
        acceptanceRepository.save(acceptance);
    }

    @Override
    public List<AcceptanceDto> search(Specification<Acceptance> spec) {
        return executeSearch(acceptanceRepository, acceptanceMapper::toDto, spec);
    }
}
