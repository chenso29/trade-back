package src.main.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.models.entity.util.BonusProgram;
import com.trade_accounting.models.entity.company.ContractorGroup;
import com.trade_accounting.models.dto.util.BonusProgramDto;
import com.trade_accounting.repositories.util.BonusProgramRepository;
import com.trade_accounting.repositories.company.ContractorGroupRepository;
import com.trade_accounting.services.interfaces.util.BonusProgramService;
import com.trade_accounting.utils.mapper.util.BonusProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ivanov Daniil
 * @version 1.0.0
 */

@Service
@Transactional
@RequiredArgsConstructor
public class BonusProgramServiceImpl implements BonusProgramService {

    private final BonusProgramRepository bonusProgramRepository;
    private final ContractorGroupRepository contractorGroupRepository;
    private final BonusProgramMapper bonusProgramMapper;

    @Override
    public List<BonusProgramDto> getAll() {
        return bonusProgramRepository.findAll()
                .stream()
                .map(bonusProgramMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BonusProgramDto getById(Long id) {
        Optional<BonusProgram> bonusProgram = bonusProgramRepository.findById(id);
        return bonusProgramMapper.toDto(bonusProgram.orElse(new BonusProgram()));
    }

    @Override
    public BonusProgramDto create(BonusProgramDto dto) {
        BonusProgram bonusProgram = bonusProgramMapper.toModel(dto);
        List<ContractorGroup> contractorGroupList =  dto.getContractorGroupIds().stream()
                .map(contractorGroupRepository::getContractorGroupById)
                .collect(Collectors.toList());
        bonusProgram.setContractorGroups(contractorGroupList);
        return bonusProgramMapper.toDto(bonusProgramRepository.save(bonusProgram));
    }

    @Override
    public BonusProgramDto update(BonusProgramDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        bonusProgramRepository.deleteById(id);
    }
}
