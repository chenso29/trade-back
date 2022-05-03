package src.main.java.com.trade_accounting.services.interfaces.fias;

import com.trade_accounting.models.dto.fias.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> getAll();

    CityDto getById(Long id);

    CityDto create(CityDto cityDto);

    CityDto update(CityDto cityDto);

    void deleteById(Long id);
}
