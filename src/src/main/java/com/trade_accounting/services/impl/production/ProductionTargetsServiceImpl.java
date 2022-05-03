package src.main.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.production.ProductionTargets;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.production.ProductionTargetsDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.production.ProductionTargetsRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.production.ProductionTargetsService;
import com.trade_accounting.utils.mapper.production.ProductionTargetsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductionTargetsServiceImpl implements ProductionTargetsService {

    private final ProductionTargetsRepository productionTargetsRepository;
    private final ProductionTargetsMapper productionTargetsMapper;
    private final CompanyRepository companyRepository;
    private final WarehouseRepository warehouseRepository;


    @Override
    public List<ProductionTargetsDto> getAll() {
        return productionTargetsRepository.findAll()
                .stream().map(productionTargetsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductionTargetsDto getById(Long id) {
        return productionTargetsMapper.toDto(productionTargetsRepository.getOne(id));
    }

    @Override
    public ProductionTargetsDto create(ProductionTargetsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public ProductionTargetsDto update(ProductionTargetsDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        productionTargetsRepository.deleteById(id);
    }

    @Override
    public List<ProductionTargetsDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<ProductionTargets> all = productionTargetsRepository.findAll();
            return all.stream().map(productionTargetsMapper::toDto).collect(Collectors.toList());
        } else {
            List<ProductionTargets> list = productionTargetsRepository.search(searchTerm);
            return list.stream().map(productionTargetsMapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<ProductionTargetsDto> search(Specification<ProductionTargets> spec) {
        return executeSearch(productionTargetsRepository, productionTargetsMapper::toDto, spec);
    }

    private ProductionTargetsDto saveOrUpdate(ProductionTargetsDto dto) {

        ProductionTargets productionTargets = new ProductionTargets();

        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        Warehouse materialWarehouse = warehouseRepository.getWarehouseById(dto.getMaterialWarehouse());
        Warehouse productionWarehouse = warehouseRepository.getWarehouseById(dto.getProductionWarehouse());
        LocalDateTime date = LocalDateTime.parse(dto.getDate().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime deliveryPlannedMoment = LocalDateTime.parse(dto.getDeliveryPlannedMoment().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime productionStart = LocalDateTime.parse(dto.getProductionStart().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime productionEnd = LocalDateTime.parse(dto.getProductionEnd().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime updated = LocalDateTime.parse(dto.getUpdated().substring(0, 16).replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        productionTargets.setId(dto.getId());
        productionTargets.setDate(date);
        productionTargets.setCompany(company);
        productionTargets.setDeliveryPlannedMoment(deliveryPlannedMoment);
        productionTargets.setMaterialWarehouse(materialWarehouse);
        productionTargets.setProductionWarehouse(productionWarehouse);
        productionTargets.setProductionStart(productionStart);
        productionTargets.setProductionEnd(productionEnd);
        productionTargets.setShared(dto.getShared());
        productionTargets.setOwner(dto.getOwner());
        productionTargets.setEmployeeOwner(dto.getEmployeeOwner());
        productionTargets.setPublished(dto.getPublished());
        productionTargets.setPrinted(dto.getPrinted());
        productionTargets.setDescription(dto.getDescription());
        productionTargets.setUpdated(updated);
        productionTargets.setUpdatedByName(dto.getUpdated());

        return productionTargetsMapper.toDto(productionTargetsRepository.save(productionTargets));

    }

}
