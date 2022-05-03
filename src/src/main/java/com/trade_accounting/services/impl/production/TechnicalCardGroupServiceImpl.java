package src.main.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.dto.production.TechnicalCardGroupDto;
import com.trade_accounting.repositories.production.TechnicalCardGroupRepository;
import com.trade_accounting.services.interfaces.production.TechnicalCardGroupService;
import com.trade_accounting.utils.mapper.production.TechnicalCardGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicalCardGroupServiceImpl implements TechnicalCardGroupService {

    private final TechnicalCardGroupRepository technicalCardGroupRepository;

    private final TechnicalCardGroupMapper technicalCardGroupMapper;

    @Override
    public List<TechnicalCardGroupDto> getAll() {
        return technicalCardGroupRepository.findAll().stream()
                .map(technicalCardGroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TechnicalCardGroupDto getById(Long id) {
        return technicalCardGroupMapper.toDto(
                technicalCardGroupRepository.getOne(id));
    }

    @Override
    public TechnicalCardGroupDto create(TechnicalCardGroupDto dto) {
        return technicalCardGroupMapper.toDto(technicalCardGroupRepository
                .save(technicalCardGroupMapper.toModel(dto)));
    }

    @Override
    public TechnicalCardGroupDto update(TechnicalCardGroupDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        technicalCardGroupRepository.deleteById(id);
    }
}
