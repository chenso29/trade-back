package src.main.java.com.trade_accounting.services.impl.warehouse;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.warehouse.Shipment;
import com.trade_accounting.models.entity.warehouse.ShipmentProduct;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.warehouse.ShipmentDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.warehouse.ShipmentProductRepository;
import com.trade_accounting.repositories.warehouse.ShipmentRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.warehouse.ShipmentService;
import com.trade_accounting.utils.mapper.warehouse.ShipmentMapper;
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
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final WarehouseRepository warehouseRepository;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentProductRepository shipmentProductRepository;

    @Override
    public List<ShipmentDto> search(Specification<Shipment> spec) {
        return executeSearch(shipmentRepository, shipmentMapper::toDto, spec);
    }

    @Override
    public List<ShipmentDto> searchString(String search) {
        return shipmentRepository.searchString(search).stream()
                .map(shipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipmentDto> getAll() {
        return shipmentRepository.findAll().stream()
                .map(shipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentDto getById(Long id) {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        return shipmentMapper.toDto(shipment.orElse(new Shipment()));
    }

    @Override
    public ShipmentDto create(ShipmentDto shipmentDto) {
        Shipment shipmentSaved = shipmentMapper.toModel(shipmentDto);

        Company company = companyRepository.getCompaniesById(shipmentDto.getCompanyId());
        Contractor contractor = contractorRepository.getContractorById(shipmentDto.getContractorId());
        Warehouse warehouse = warehouseRepository.getOne(shipmentDto.getWarehouseId());
        List<ShipmentProduct> shipmentProductList = shipmentDto.getShipmentProductsIds().stream()
                .map(shipmentProductRepository::getOne)
                .collect(Collectors.toList());

        shipmentSaved.setShipmentProducts(shipmentProductList);
        shipmentSaved.setCompany(company);
        shipmentSaved.setContractor(contractor);
        shipmentSaved.setWarehouse(warehouse);

        return shipmentMapper.toDto(shipmentRepository.save(shipmentSaved));
    }

    @Override
    public ShipmentDto update(ShipmentDto shipmentDto) {
        Shipment shipment = shipmentRepository.save(shipmentMapper.toModel(shipmentDto));
        return shipmentMapper.toDto(shipment);
    }

    @Override
    public void deleteById(Long id) {
        shipmentRepository.deleteById(id);
    }

    @Override
    public void moveToRecyclebin(long id) {
        Shipment shipment = shipmentRepository.getOne(id);
        shipment.setIsRecyclebin(true);
        shipmentRepository.save(shipment);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        Shipment shipment = shipmentRepository.getOne(id);
        shipment.setIsRecyclebin(false);
        shipmentRepository.save(shipment);
    }
}
