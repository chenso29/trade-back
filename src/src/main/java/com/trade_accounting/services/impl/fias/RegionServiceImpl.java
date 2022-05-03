package src.main.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.RegionDto;
import com.trade_accounting.models.entity.fias.Region;
import com.trade_accounting.repositories.fias.RegionRepository;
import com.trade_accounting.services.interfaces.fias.RegionService;
import com.trade_accounting.utils.mapper.fias.RegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionMapper regionMapper;
    private final RegionRepository repository;

    @Override
    public List<RegionDto> getAll() {
        List<Region> regions = repository.findAll();
        return regions.stream().map(regionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RegionDto getById(Long id) {
        Region region = repository.findById(id).orElse(new Region());
        return regionMapper.toDto(region);
    }

    @Override
    public RegionDto create(RegionDto regionDto) {
        Region region = repository.save(regionMapper.toModel(regionDto));
        return regionMapper.toDto(region);
    }

    @Override
    public RegionDto update(RegionDto regionDto) {
        return regionMapper.toDto(repository.save(regionMapper.toModel(regionDto)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
