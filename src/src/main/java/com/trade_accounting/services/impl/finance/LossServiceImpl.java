package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.finance.Loss;
import com.trade_accounting.models.entity.finance.LossProduct;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.dto.finance.LossDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.finance.LossProductRepository;
import com.trade_accounting.repositories.finance.LossRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.services.interfaces.finance.LossService;
import com.trade_accounting.utils.mapper.finance.LossMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LossServiceImpl implements LossService {
    private final LossRepository lossRepository;
    private final LossProductRepository lossProductRepository;
    private final CompanyRepository companyRepository;
    private final WarehouseRepository warehouseRepository;
    private final LossMapper lossMapper;

    @Override
    public List<LossDto> getAll() {
        return lossRepository.findAll().stream()
                .map(lossMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LossDto getById(Long id) {
        return lossMapper.toDto(lossRepository.getOne(id));
    }

    @Override
    public LossDto create(LossDto dto) {
        return saveOrUpdate(dto);
    }


    @Override
    public LossDto update(LossDto dto) {
        return saveOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        lossRepository.deleteById(id);
    }

    private LossDto saveOrUpdate(LossDto dto) {
        Loss loss = lossMapper.toModel(dto);

        return lossMapper.toDto(lossRepository.save(loss));
    }

    @Override
    public List<LossDto> search(Specification<Loss> spec) {
        return executeSearch(lossRepository, lossMapper::toDto, spec);
    }

    @Override
    public void moveToRecyclebin(long id) {
        Loss loss = lossRepository.getOne(id);
        loss.setIsRecyclebin(true);
        lossRepository.save(loss);
    }

    @Override
    public void restoreFromRecyclebin(long id) {
        Loss loss = lossRepository.getOne(id);
        loss.setIsRecyclebin(false);
        lossRepository.save(loss);
    }
}
