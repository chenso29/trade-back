package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.dto.warehouse.SerialNumbersDto;
import com.trade_accounting.models.entity.warehouse.SerialNumbers;
import com.trade_accounting.repositories.warehouse.ProductRepository;
import com.trade_accounting.repositories.warehouse.SerialNumbersRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.warehouse.SerialNumbersService;
import com.trade_accounting.utils.mapper.warehouse.SerialNumbersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SerialNumbersServiceImpl implements SerialNumbersService {

    private final SerialNumbersRepository serialNumbersRepository;
    private final SerialNumbersMapper serialNumbersMapper;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public List<SerialNumbersDto> getAll() {
        return serialNumbersRepository.findAll().stream()
                .map(serialNumbersMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SerialNumbersDto getById(Long id) {
        return serialNumbersMapper.toDto(serialNumbersRepository.getOne(id));
    }

    @Override
    public SerialNumbersDto create(SerialNumbersDto dto) {
        SerialNumbers serialNumbers = serialNumbersMapper.toModel(dto);
        serialNumbers.setProduct(productRepository.getOne(dto.getProductId()));
        serialNumbers.setWarehouse(warehouseRepository.getOne(dto.getWarehouseId()));
        return serialNumbersMapper.toDto(serialNumbersRepository.save(serialNumbers));
    }

    @Override
    public SerialNumbersDto update(SerialNumbersDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        serialNumbersRepository.deleteById(id);
    }

    @Override
    public List<SerialNumbersDto> search(Specification<SerialNumbers> spec) {
        return executeSearch(serialNumbersRepository, serialNumbersMapper::toDto, spec);
    }

    @Override
    public List<SerialNumbersDto> searchByFilter(String search) {
        return serialNumbersRepository.searchString(search).stream()
                .map(serialNumbersMapper::toDto)
                .collect(Collectors.toList());

    }
}
