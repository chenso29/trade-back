package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.ContractorGroup;
import com.trade_accounting.models.dto.company.ContractorGroupDto;
import com.trade_accounting.repositories.company.ContractorGroupRepository;
import com.trade_accounting.services.interfaces.company.ContractorGroupService;
import com.trade_accounting.utils.mapper.company.ContractorGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractorGroupServiceImpl implements ContractorGroupService {

    private final ContractorGroupRepository contractorGroupRepository;
    private final ContractorGroupMapper contractorGroupMapper;

    @Override
    public List<ContractorGroupDto> getAll() {
        return contractorGroupRepository.getAll();
    }

    @Override
    public ContractorGroupDto getById(Long id) {
        return contractorGroupRepository.getById(id);
    }

    @Override
    public ContractorGroupDto create(ContractorGroupDto dto) {
        ContractorGroup contractorGroupSaved = contractorGroupRepository.save(contractorGroupMapper.toModel(dto));
        dto.setId(contractorGroupSaved.getId());
        return dto;
    }

    @Override
    public ContractorGroupDto update(ContractorGroupDto dto) {
        create(dto);
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        contractorGroupRepository.deleteById(id);
    }
}
