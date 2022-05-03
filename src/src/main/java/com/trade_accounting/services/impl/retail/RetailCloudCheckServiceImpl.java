package src.main.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.dto.retail.RetailCloudCheckDto;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.retail.RetailCloudCheck;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.retail.RetailCloudCheckRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.repositories.units.CurrencyRepository;
import com.trade_accounting.services.interfaces.retail.RetailCloudCheckService;
import com.trade_accounting.utils.mapper.retail.RetailCloudCheckMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RetailCloudCheckServiceImpl implements RetailCloudCheckService {

    private final RetailCloudCheckRepository retailCloudCheckRepository;
    private final RetailStoreRepository retailStoreRepository;
    private final RetailCloudCheckMapper retailCloudCheckMapper;
    private final CurrencyRepository currencyRepository;
    private final EmployeeRepository employeeRepository;

    public RetailCloudCheckServiceImpl(RetailCloudCheckRepository retailCloudCheckRepository, RetailStoreRepository retailStoreRepository, RetailCloudCheckMapper retailCloudCheckMapper, CurrencyRepository currencyRepository, EmployeeRepository employeeRepository) {
        this.retailCloudCheckRepository = retailCloudCheckRepository;
        this.retailStoreRepository = retailStoreRepository;
        this.retailCloudCheckMapper = retailCloudCheckMapper;
        this.currencyRepository = currencyRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<RetailCloudCheckDto> getAll() {
        return retailCloudCheckRepository.findAll().stream()
                .map(retailCloudCheckMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailCloudCheckDto getById(Long id) {
        Optional<RetailCloudCheck> retailCloudCheck = retailCloudCheckRepository.findById(id);
        return retailCloudCheckMapper.toDto(
                retailCloudCheck.orElse(new RetailCloudCheck()));
    }

    @Override
    public RetailCloudCheckDto create(RetailCloudCheckDto dto) {
        RetailCloudCheck retailCloudCheck = retailCloudCheckMapper.toModel(dto);
        Optional<RetailStore> initiator = retailStoreRepository.findById(dto.getInitiatorId());
        Optional<RetailStore> fiscalizationPoint = retailStoreRepository.findById(dto.getFiscalizationPointId());
//        Currency currency = currencyRepository.getById(dto.getId()); нужно прописать репозиторий CurrencyRepository
        Optional<Employee> cashier = employeeRepository.findById(dto.getId());

        retailCloudCheck.setInitiator(initiator.orElse(null));
        retailCloudCheck.setFiscalizationPoint(fiscalizationPoint.orElse(null));
//        retailCloudCheck.setCurrency(currency);
        retailCloudCheck.setCashier(cashier.get());

        return retailCloudCheckMapper.toDto(retailCloudCheckRepository.save(retailCloudCheck));
    }

    @Override
    public RetailCloudCheckDto update(RetailCloudCheckDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailCloudCheckRepository.deleteById(id);
    }

    @Override
    public List<RetailCloudCheckDto> search(Specification<RetailCloudCheck> spec) {
        return executeSearch(retailCloudCheckRepository, retailCloudCheckMapper::toDto, spec);
    }
}
