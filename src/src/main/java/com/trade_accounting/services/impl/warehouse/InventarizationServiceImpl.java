package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.warehouse.Inventarization;
import com.trade_accounting.models.entity.warehouse.InventarizationProduct;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.InventarizationDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.warehouse.InventarizationProductRepository;
import com.trade_accounting.repositories.warehouse.InventarizationRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.warehouse.InventarizationService;
import com.trade_accounting.utils.mapper.warehouse.InventarizationMapper;
import com.trade_accounting.utils.mapper.warehouse.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InventarizationServiceImpl implements InventarizationService {

    private final InventarizationRepository inventarizationRepository;
    private final InventarizationProductRepository inventarizationProductRepository;
    private final WarehouseRepository warehouseRepository;
    private final CompanyRepository companyRepository;
    private final WarehouseMapper warehouseMapper;
    private final InventarizationMapper inventarizationMapper;

    @Override
    public List<InventarizationDto> getAll() {
        return inventarizationRepository.findAll().stream()
                .map(inventarizationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventarizationDto getById(Long id) {
        Optional<Inventarization> inventarization = inventarizationRepository.findById(id);
        return inventarizationMapper.toDto(inventarization.orElse(new Inventarization()));
    }

    @Override
    public InventarizationDto create(InventarizationDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public InventarizationDto update(InventarizationDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        inventarizationRepository.deleteById(id);
    }

    private InventarizationDto saveOrUpdate(InventarizationDto dto) {
        Inventarization inventarization = inventarizationMapper.toModel(dto);
        Warehouse warehouse = warehouseMapper.toModel(warehouseRepository.getById(dto.getWarehouseId()));
        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        LocalDateTime date = LocalDateTime.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        List<InventarizationProduct> inventarizationProducts = dto.getInventarizationProductIds()
                .stream()
                .map(id -> inventarizationProductRepository.findById(id).orElse(null))
                .collect(Collectors.toList());

        inventarization.setWarehouse(warehouse);
        inventarization.setCompany(company);
        inventarization.setDate(date);
        inventarization.setInventarizationProducts(inventarizationProducts);

        return inventarizationMapper.toDto(inventarizationRepository.save(inventarization));
    }

    @Override
    public List<InventarizationDto> search(Specification<Inventarization> spec) {
        List<Inventarization> inventarizationList = inventarizationRepository.findAll(spec);
        List<InventarizationDto> inventarizationDtoList = new ArrayList<>();
        for (Inventarization io : inventarizationList) {
            inventarizationDtoList.add(inventarizationMapper.toDto(io));
        }
        return inventarizationDtoList;
    }

    @Override
    public List<InventarizationDto> search(String search) {
        return inventarizationRepository.search(search).stream()
                .map(inventarizationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void moveToRecyclebin(long id) {
        Inventarization inventarization = inventarizationRepository.getOne(id);
        inventarization.setIsRecyclebin(true);
        inventarizationRepository.save(inventarization);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        Inventarization inventarization = inventarizationRepository.getOne(id);
        inventarization.setIsRecyclebin(false);
        inventarizationRepository.save(inventarization);
    }
}
