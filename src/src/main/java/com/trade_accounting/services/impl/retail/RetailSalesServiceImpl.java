package src.main.java.com.trade_accounting.services.impl.retail;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.retail.RetailSales;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.dto.retail.RetailSalesDto;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.retail.RetailSalesRepository;
import com.trade_accounting.repositories.retail.RetailStoreRepository;
import com.trade_accounting.services.interfaces.retail.RetailSalesService;
import com.trade_accounting.utils.mapper.retail.RetailSalesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RetailSalesServiceImpl implements RetailSalesService {

    private final RetailSalesRepository retailSalesRepository;
    private final RetailStoreRepository retailStoreRepository;
    private final ContractorRepository contractorRepository;
    private final CompanyRepository companyRepository;
    private final RetailSalesMapper retailSalesMapper;

    @Override
    public List<RetailSalesDto> getAll() {
        return retailSalesRepository.findAll().stream()
                .map(retailSalesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RetailSalesDto getById(Long id) {
        Optional<RetailSales> retailSales = retailSalesRepository.findById(id);
        return retailSalesMapper.toDto(
                retailSales.orElse(new RetailSales()));
    }

    @Override
    public RetailSalesDto create(RetailSalesDto dto) {
        RetailSales retailSales = retailSalesMapper.toModel(dto);
        Company company = companyRepository.getCompaniesById(dto.getCompanyId());
        Optional<RetailStore> retailStore = retailStoreRepository.findById(dto.getRetailStoreId());
        Contractor contactor = contractorRepository.getContractorById(dto.getContractorId());

        retailSales.setCompany(company);
        retailSales.setRetailStore(retailStore.orElse(null));
        retailSales.setContractor(contactor);

        return retailSalesMapper.toDto(retailSalesRepository.save(retailSales));
    }

    @Override
    public RetailSalesDto update(RetailSalesDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        retailSalesRepository.deleteById(id);
    }

    @Override
    public List<RetailSalesDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<RetailSales> all = retailSalesRepository.findAll();
            return all.stream().map(retailSalesMapper::toDto).collect(Collectors.toList());
        } else {
            List<RetailSales> list = retailSalesRepository.search(searchTerm);
            return list.stream().map(retailSalesMapper::toDto).collect(Collectors.toList());

        }
    }

    @Override
    public List<RetailSalesDto> search(Specification<RetailSales> spec) {
        List<RetailSales> retailSalesList = retailSalesRepository.findAll(spec);

        List<RetailSalesDto> retailSalesDtoList = new ArrayList<>();
        for(RetailSales io : retailSalesList) {
            retailSalesDtoList.add(retailSalesMapper.toDto(io));
        }
        return retailSalesDtoList;
    }

}
