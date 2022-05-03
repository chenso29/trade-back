package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.WarehouseDto;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.warehouse.WarehouseService;
import com.trade_accounting.utils.SortNumberConverter;
import com.trade_accounting.utils.mapper.warehouse.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    private final WarehouseMapper warehouseMapper;

    @Override
    public List<WarehouseDto> getAll() {
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseDto getById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        return warehouseMapper.toDto(
                warehouse.orElse(
                        new Warehouse()
                )
        );
    }

    @Override
    public WarehouseDto create(WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseMapper.toModel(warehouseDto);
        warehouse.setSortNumber(
                SortNumberConverter.convert(warehouseDto.getSortNumber())
        );
        warehouseDto.setId(warehouse.getId());
        warehouseRepository.save(warehouse);
        return warehouseDto;
    }


    @Override
    public WarehouseDto update(WarehouseDto warehouseDto) {
        create(warehouseDto);
        return warehouseDto;
    }

    @Override
    public void deleteById(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public List<WarehouseDto> searchByString(String text) {
        return warehouseRepository.getBySearch(text).stream()
                .map(warehouseMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<WarehouseDto> search(Specification<Warehouse> specification) {
        return executeSearch(warehouseRepository, warehouseMapper::toDto, specification);
    }
}
