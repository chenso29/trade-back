package src.main.java.com.trade_accounting.services.interfaces.fias;

import com.trade_accounting.models.dto.fias.DistrictDto;

import java.util.List;

public interface DistrictService {
    List<DistrictDto> getAll();

    DistrictDto getById(Long id);

    DistrictDto create(DistrictDto districtDto);

    DistrictDto update(DistrictDto districtDto);

    void deleteById(Long id);
}
