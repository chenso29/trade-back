package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.finance.ReturnToSupplier;
import com.trade_accounting.models.dto.finance.ReturnToSupplierDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.finance.ReturnToSupplierRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.finance.ReturnToSupplierService;
import com.trade_accounting.utils.mapper.finance.ReturnToSupplierMapper;
import com.trade_accounting.utils.mapper.warehouse.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnToSupplierServiceImpl implements ReturnToSupplierService {

    private final ReturnToSupplierRepository returnsToSuppliersRepository;
    private final WarehouseMapper warehouseMapper;
    private final CompanyRepository companyRepository;
    private final ContractorRepository contractorRepository;
    private final ContractRepository contractRepository;
    private final WarehouseRepository warehouseRepository;
    private final ReturnToSupplierMapper returnToSupplierMapper;

    @Override
    public List<ReturnToSupplierDto> getAll() {
        return returnsToSuppliersRepository.getAll();
    }

    @Override
    public ReturnToSupplierDto getById(Long id) {
        Optional<ReturnToSupplier> returnsToSuppliersById = returnsToSuppliersRepository.findById(id);
        return returnToSupplierMapper.toDto(returnsToSuppliersById.orElse(new ReturnToSupplier()));
    }

    @Override
    public ReturnToSupplierDto create(ReturnToSupplierDto dto) {
        ReturnToSupplier returnsToSuppliers = ReturnToSupplier.builder().id(dto.getId())
                .date(dto.getDate())
                .contractor(contractorRepository.getOne(dto.getContractorId()))
                .contract(contractRepository.getById(dto.getContractId()))
                .warehouse(warehouseMapper.toModel(warehouseRepository.getById(dto.getWarehouseId())))
                .company(companyRepository.getCompaniesById(dto.getCompanyId()))
                .comment(dto.getComment())
                .isPrint(dto.getIsPrint())
                .isSend(dto.getIsSend())
                .build();
        return returnToSupplierMapper.toDto(returnsToSuppliersRepository.save(returnsToSuppliers));
    }

    @Override
    public ReturnToSupplierDto update(ReturnToSupplierDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        returnsToSuppliersRepository.deleteById(id);
    }

    @Override
    public List<ReturnToSupplierDto> searchByString(String nameFilter) {
        if (nameFilter.matches("[0-9]+")) {
            return returnsToSuppliersRepository.searchById(Long.parseLong(nameFilter));
        } else if (nameFilter.equals("null") || nameFilter.isEmpty()) {
            return returnsToSuppliersRepository.getAll();
        } else {
            return returnsToSuppliersRepository.searchByNameFilter(nameFilter);
        }
    }

    @Override
    public List<ReturnToSupplierDto> search(Specification<ReturnToSupplier> spec) {
        return executeSearch(returnsToSuppliersRepository, returnToSupplierMapper::toDto, spec);
    }
}
