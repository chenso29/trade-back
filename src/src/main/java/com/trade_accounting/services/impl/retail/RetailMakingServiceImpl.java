package src.main.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.dto.retail.RetailMakingDto;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.retail.RetailMaking;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.retail.RetailMakingRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.services.interfaces.retail.RetailMakingService;
import com.trade_accounting.utils.mapper.retail.RetailMakingMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RetailMakingServiceImpl implements RetailMakingService {

    private final RetailMakingRepository retailMakingRepository;
    private final RetailMakingMapper retailMakingMapper;
    private final RetailStoreRepository retailStoreRepository;
    private final CompanyRepository companyRepository;

    public RetailMakingServiceImpl(RetailMakingRepository retailMakingRepository,
                                   RetailMakingMapper retailMakingMapper, RetailStoreRepository retailStoreRepository, CompanyRepository companyRepository) {
        this.retailMakingRepository = retailMakingRepository;
        this.retailMakingMapper = retailMakingMapper;
        this.retailStoreRepository = retailStoreRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<RetailMakingDto> getAll() {
        return retailMakingRepository.findAll().stream()
                .map(retailMakingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailMakingDto getById(Long id) {
        Optional<RetailMaking> retailCloudCheck = retailMakingRepository.findById(id);
        return retailMakingMapper.toDto(retailCloudCheck.orElse(new RetailMaking()));
    }

    @Override
    public RetailMakingDto create(RetailMakingDto dto) {
        RetailMaking retailMaking = retailMakingMapper.toModel(dto);
        Optional<RetailStore> retailStore = retailStoreRepository.findById(dto.getRetailStoreId());
        Company company = companyRepository.getCompaniesById(dto.getRetailStoreId());
        retailMaking.setRetailStore(retailStore.orElse(null));
        retailMaking.setCompany(company);
        return retailMakingMapper.toDto(retailMakingRepository.save(retailMaking));
    }

    @Override
    public RetailMakingDto update(RetailMakingDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailMakingRepository.deleteById(id);
    }

    @Override
    public List<RetailMakingDto> search(Specification<RetailMaking> spec) {
        List<RetailMaking> retailReturnList = retailMakingRepository.findAll(spec);

        List<RetailMakingDto> retailMakingDtoList = new ArrayList<>();
        for (RetailMaking retailMaking : retailReturnList) {
            retailMakingDtoList.add(retailMakingMapper.toDto(retailMaking));
        }
        return retailMakingDtoList;
    }

    @Override
    public List<RetailMakingDto> search(String search) {
        if ("null".equals(search) || search.isEmpty()) {
            List<RetailMaking> list = retailMakingRepository.findAll();
            return list.stream().map(retailMakingMapper::toDto).collect(Collectors.toList());
        } else {
            List<RetailMaking> list = retailMakingRepository.search(search);
            return list.stream().map(retailMakingMapper::toDto).collect(Collectors.toList());
        }
    }
}
