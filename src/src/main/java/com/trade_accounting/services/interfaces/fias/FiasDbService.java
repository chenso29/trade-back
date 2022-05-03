package src.main.java.com.trade_accounting.services.interfaces.fias;

import com.trade_accounting.models.dto.fias.FiasAddressModelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FiasDbService {
    List<FiasAddressModelDto> getAll();

    FiasAddressModelDto getById(Long id);

    FiasAddressModelDto create(FiasAddressModelDto dto);

    void createAll(List<FiasAddressModelDto> dtoList);

    FiasAddressModelDto update(FiasAddressModelDto dto);

    void deleteById(Long id);

    List<FiasAddressModelDto> findAllByLevel(String level);

    List<FiasAddressModelDto> findAllByAoguid(String level);
}
