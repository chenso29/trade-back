package src.main.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.DistrictDto;
import com.trade_accounting.models.entity.fias.District;
import com.trade_accounting.repositories.fias.DistrictRepository;
import com.trade_accounting.repositories.fias.RegionRepository;
import com.trade_accounting.services.interfaces.fias.DistrictService;
import com.trade_accounting.utils.mapper.fias.DistrictMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictMapper districtMapper;
    private final DistrictRepository repository;
    private final RegionRepository regionRepository;

    @Override
    public List<DistrictDto> getAll() {
        List<District> districts = repository.findAll();
        return districts.stream().map(districtMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DistrictDto getById(Long id) {
        District district = repository.findById(id).orElse(new District());
        return districtMapper.toDto(district);
    }

    @Override
    public DistrictDto create(DistrictDto districtDto) {
        District district1 = districtMapper.toModel(districtDto);
        District district = repository.save(district1);
        return districtMapper.toDto(district);
    }

    @Override
    public DistrictDto update(DistrictDto districtDto) {
        return districtMapper.toDto(repository.save(districtMapper.toModel(districtDto)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
