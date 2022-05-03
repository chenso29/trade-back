package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.dto.company.ContractorStatusDto;
import com.trade_accounting.repositories.company.ContractorStatusRepository;
import com.trade_accounting.services.interfaces.company.ContractorStatusService;
import com.trade_accounting.utils.mapper.company.ContractorStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractorStatusServiceImpl implements ContractorStatusService {

    private final ContractorStatusRepository contractorStatusRepository;

    private final ContractorStatusMapper contractorStatusMapper;

    @Override
    public ContractorStatusDto getById(Long id) {
        return contractorStatusMapper.toDto(contractorStatusRepository.getOne(id));
    }

    @Override
    public ContractorStatusDto create(ContractorStatusDto dto) {
        return contractorStatusMapper.toDto(contractorStatusRepository.save(contractorStatusMapper.toModel(dto)));
    }

    @Override
    public ContractorStatusDto update(ContractorStatusDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        contractorStatusRepository.deleteById(id);
    }

    @Override
    public List<ContractorStatusDto> getAll() {
        return contractorStatusRepository.findAll().stream()
                .map(contractorStatusMapper::toDto)
                .collect(Collectors.toList());
    }
}
