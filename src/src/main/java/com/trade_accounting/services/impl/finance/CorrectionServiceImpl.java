package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.finance.Correction;
import com.trade_accounting.models.entity.finance.CorrectionProduct;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.finance.CorrectionDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.finance.CorrectionProductRepository;
import com.trade_accounting.repositories.finance.CorrectionRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.finance.CorrectionService;
import com.trade_accounting.utils.mapper.finance.CorrectionMapper;
import com.trade_accounting.utils.mapper.warehouse.WarehouseMapper;
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
public class CorrectionServiceImpl implements CorrectionService {

    private final CorrectionRepository correctionRepository;
    private final WarehouseRepository warehouseRepository;
    private final CompanyRepository companyRepository;
    private final CorrectionProductRepository correctionProductRepository;
    private final WarehouseMapper warehouseMapper;
    private final CorrectionMapper correctionMapper;

    @Override
    public List<CorrectionDto> getAll() {
        return correctionRepository.getAll().stream()
                .map(correctionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CorrectionDto> search(Specification<Correction> spec) {
        return executeSearch(correctionRepository, correctionMapper::toDto, spec);
    }

    @Override
    public CorrectionDto getById(Long id) {
        return correctionMapper.toDto(correctionRepository.getCorrectionById(id));
    }

    @Override
    public CorrectionDto create(CorrectionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public CorrectionDto update(CorrectionDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        correctionRepository.deleteById(id);
    }

    private CorrectionDto saveOrUpdate(CorrectionDto dto) {
        Correction correction = correctionMapper.toModel(dto);
        Warehouse warehouse = warehouseMapper.toModel(warehouseRepository.getById(dto.getWarehouseId()));
        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        LocalDateTime date = LocalDateTime.parse(dto.getDate());

        List<CorrectionProduct> correctionProducts = dto.getCorrectionProductIds().stream()
                .map(id -> correctionProductRepository.findById(id).orElse(null)).collect(Collectors.toList());
        correction.setId(dto.getId());
        correction.setWarehouse(warehouse);
        correction.setCompany(company);
        correction.setDate(date);
        correction.setCorrectionProducts(correctionProducts);

        return correctionMapper.toDto(correctionRepository.save(correction));
    }

    @Override
    public void moveToRecyclebin(long id) {
        Correction correction = correctionRepository.getCorrectionById(id);
        correction.setIsRecyclebin(true);
        correctionRepository.save(correction);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        Correction correction = correctionRepository.getCorrectionById(id);
        correction.setIsRecyclebin(false);
        correctionRepository.save(correction);
    }
}

