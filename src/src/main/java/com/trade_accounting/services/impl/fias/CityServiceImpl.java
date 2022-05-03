package src.main.java.com.trade_accounting.services.impl.fias;

import com.trade_accounting.models.dto.fias.CityDto;
import com.trade_accounting.models.entity.fias.City;
import com.trade_accounting.repositories.fias.CityRepository;
import com.trade_accounting.repositories.fias.DistrictRepository;
import com.trade_accounting.services.interfaces.fias.CityService;
import com.trade_accounting.utils.mapper.fias.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityMapper cityMapper;
    private final CityRepository repository;
    private final DistrictRepository districtRepository;

    @Override
    public List<CityDto> getAll() {
        List<City> cityList = repository.findAll();
        return cityList.stream().map(cityMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CityDto getById(Long id) {
        City city = repository.findById(id).orElse(new City());
        return cityMapper.toDto(city);
    }

    @Override
    public CityDto create(CityDto cityDto) {
        City city1 = cityMapper.toModel(cityDto);
        City city = repository.save(city1);
        return cityMapper.toDto(city);
    }

    @Override
    public CityDto update(CityDto cityDto) {
        City city = repository.save(cityMapper.toModel(cityDto));
        return cityMapper.toDto(city);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
