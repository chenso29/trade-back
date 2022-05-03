package src.main.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.production.StagesProduction;
import com.trade_accounting.models.dto.production.StagesProductionDto;
import com.trade_accounting.repositories.production.StagesProductionRepository;
import com.trade_accounting.services.interfaces.production.StagesProductionService;
import com.trade_accounting.utils.mapper.production.StagesProductionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StagesProductionServiceImpl implements StagesProductionService {

    private final StagesProductionRepository stagesProductionRepository;

    private final StagesProductionMapper stagesProductionMapper;

    @Override
    public List<StagesProductionDto> getAll() {
        return stagesProductionRepository.findAll().stream()
                .map(stagesProductionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StagesProductionDto getById(Long id) {
        StagesProduction stagesProduction = stagesProductionRepository.getOne(id);
        return stagesProductionMapper.toDto(stagesProduction);
    }

    @Override
    public StagesProductionDto create(StagesProductionDto dto) {
        dto.setId(stagesProductionMapper.toModel(dto).getId());
        return stagesProductionMapper.toDto(stagesProductionRepository.save(stagesProductionMapper.toModel(dto)));
    }

    @Override
    public StagesProductionDto update(StagesProductionDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        stagesProductionRepository.deleteById(id);
    }

    @Override
    public List<StagesProductionDto> search(Specification<StagesProduction> spec) {
        List<StagesProduction> stagesProductionList = stagesProductionRepository.findAll(spec);

        List<StagesProductionDto> stagesProductionDtoList = new ArrayList<>();
        for (StagesProduction sp : stagesProductionList) {
            stagesProductionDtoList.add(stagesProductionMapper.toDto(sp));
        }

        return stagesProductionDtoList;
    }

    @Override
    public List<StagesProductionDto> search(String searchTerm) {

        if(searchTerm.equals("null") || searchTerm.isEmpty()) {
            List<StagesProduction> all = stagesProductionRepository.findAll();
            return all.stream().map(stagesProductionMapper::toDto).collect(Collectors.toList());
        } else {
            List<StagesProduction> list = stagesProductionRepository.search(searchTerm);
            return list.stream().map(stagesProductionMapper::toDto).collect(Collectors.toList());
        }
    }
}

