package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.repositories.company.AddressRepository;
import com.trade_accounting.repositories.company.BankAccountRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.LegalDetailRepository;
import com.trade_accounting.services.interfaces.company.CompanyService;
import com.trade_accounting.utils.mapper.company.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final LegalDetailRepository legalDetailRepository;
    private final BankAccountRepository bankAccountRepository;
    private final AddressRepository addressRepository;

    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDto> getAll() {
        List<Company> companys = companyRepository.findAll();
        List<CompanyDto> companyDtos = new ArrayList<>();
        for (Company company : companys) {
            companyDtos.add(companyMapper.toDto(company));
        }
        return companyDtos;
    }

    @Override
    public List<CompanyDto> search(Specification<Company> spec) {
        return executeSearch(companyRepository, companyMapper::toDto, spec);
    }

    @Override
    public CompanyDto getById(Long id) {
        Company company = companyRepository.findById(id).orElse(new Company());
        return companyMapper.toDto(company);
    }

    @Override
    public CompanyDto getByEmail(String email) {
        Company company = companyRepository.findCompanyByEmail(email);
        return companyMapper.toDto(company);
    }

    public CompanyDto create(CompanyDto companyDto) {
        Company company = companyMapper.toModel(companyDto);
        company.setAddress(addressRepository.getOne(companyDto.getAddressId()));

        company.setLegalDetail(
                legalDetailRepository.getOne(companyDto.getLegalDetailDtoId())
        );

        company.setBankAccounts(
                companyDto.getBankAccountDtoIds().stream()
                        .map(
                                bankAccountId -> bankAccountRepository
                                        .getOne(bankAccountId)
                        )
                        .collect(Collectors.toList())
        );
        Company companySaved = companyRepository.save(company);
        if (companyDto.getId() == null) {
            companyDto.setId(companySaved.getId());
        }
        return companyDto;
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        Company company = companyMapper.toModel(companyDto);
        company.setAddress(addressRepository.getOne(companyDto.getAddressId()));
        company.setLegalDetail(
                legalDetailRepository.getOne(companyDto.getLegalDetailDtoId())
        );

        company.setBankAccounts(
                companyDto.getBankAccountDtoIds().stream()
                        .map(
                                bankAccountId -> bankAccountRepository
                                        .getOne(bankAccountId)
                        )
                        .collect(Collectors.toList())
        );
        companyRepository.save(company);
        return companyDto;
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

}