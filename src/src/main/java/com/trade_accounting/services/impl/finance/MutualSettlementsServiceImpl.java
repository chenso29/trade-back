package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.MutualSettlements;
import com.trade_accounting.models.dto.finance.MutualSettlementsDto;
import com.trade_accounting.repositories.finance.MutualSettlementsRepository;
import com.trade_accounting.services.interfaces.finance.MutualSettlementsService;
import com.trade_accounting.utils.mapper.finance.MutualSettlementsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MutualSettlementsServiceImpl implements MutualSettlementsService {

    private final MutualSettlementsRepository mutualSettlementsRepository;
    private final MutualSettlementsMapper mutualSettlementsMapper;

    @Override
    public List<MutualSettlementsDto> getAll() {
        final List<MutualSettlementsDto> collect = mutualSettlementsRepository.findAll().stream()
                .map(mutualSettlementsMapper::toDto)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public MutualSettlementsDto getById(Long id) {
        return mutualSettlementsMapper.toDto(mutualSettlementsRepository.findById(id).orElse(new MutualSettlements()));
    }

    @Override
    public MutualSettlementsDto create(MutualSettlementsDto dto) {
        MutualSettlements mutualSettlements = mutualSettlementsRepository.save(mutualSettlementsMapper.toModel(dto));
        dto.setId(mutualSettlements.getId());
        return mutualSettlementsMapper.toDto(mutualSettlements);
    }

    @Override
    public MutualSettlementsDto update(MutualSettlementsDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        mutualSettlementsRepository.deleteById(id);
    }

    @Override
    public List<MutualSettlementsDto> search(Specification<MutualSettlements> spec) {
        return executeSearch(mutualSettlementsRepository, mutualSettlementsMapper::toDto, spec) ;
    }
}
