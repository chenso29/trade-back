package src.main.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.dto.retail.RetailShiftDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.retail.RetailShift;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.retail.RetailShiftRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.retail.RetailShiftService;
import com.trade_accounting.utils.mapper.retail.RetailShiftMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RetailShiftServiceImpl implements RetailShiftService {
    private final RetailShiftRepository retailShiftRepository;
    private final RetailStoreRepository retailStoreRepository;
    private final CompanyRepository companyRepository;
    private final WarehouseRepository warehouseRepository;
    private final RetailShiftMapper retailShiftMapper;

    @Override
    public List<RetailShiftDto> getAll() {
        return retailShiftRepository.findAll().stream()
                .map(retailShiftMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailShiftDto getById(Long id) {
        Optional<RetailShift> retailShift = retailShiftRepository.findById(id);
        return retailShiftMapper.toDto(retailShift.orElse(new RetailShift()));
    }

    @Override
    public RetailShiftDto create(RetailShiftDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public RetailShiftDto update(RetailShiftDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailShiftRepository.deleteById(id);
    }

    private RetailShiftDto saveOrUpdate(RetailShiftDto dto) {
        RetailShift retailShift = retailShiftMapper.toModel(dto);

        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        Warehouse warehouse = warehouseRepository.getOne(dto.getWarehouseId());
        RetailStore retailStore = retailStoreRepository.getOne(dto.getRetailStoreId());
        LocalDateTime dateOpen = LocalDateTime.parse(dto.getDataOpen().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateClose = LocalDateTime.parse(dto.getDataClose().replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        retailShift.setRetailStore(retailStore);
        retailShift.setCompany(company);
        retailShift.setWarehouse(warehouse);
        retailShift.setDataOpen(dateOpen);
        retailShift.setDataClose(dateClose);

        return retailShiftMapper.toDto(retailShiftRepository.save(retailShift));
    }

    @Override
    public List<RetailShiftDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<RetailShift> all = retailShiftRepository.findAll();
            return all.stream().map(retailShiftMapper::toDto).collect(Collectors.toList());
        } else {
            List<RetailShift> list = retailShiftRepository.search(searchTerm);
            return list.stream().map(retailShiftMapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<RetailShiftDto> search(Specification<RetailShift> spec) {
        return executeSearch(retailShiftRepository, retailShiftMapper::toDto, spec);
    }

//    @Override
//    public List<RetailShiftDto> search(Specification<RetailShift> spec) {
//        List<RetailShift> retailShiftList = retailShiftRepository.findAll(spec);
//
//        List<RetailShiftDto> retailShiftDtoList = new ArrayList<>();
//        for(RetailShift io : retailShiftList) {
//            retailShiftDtoList.add(retailShiftMapper.toDto(io));
//        }
//        return retailShiftDtoList;
//    }
}
