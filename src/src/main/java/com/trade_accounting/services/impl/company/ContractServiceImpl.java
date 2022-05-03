package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.dto.company.ContractDto;
import com.trade_accounting.repositories.company.BankAccountRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.company.LegalDetailRepository;
import com.trade_accounting.repositories.finance.PaymentRepository;
import com.trade_accounting.services.interfaces.company.ContractService;
import com.trade_accounting.utils.mapper.company.ContractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;
    private final ContractMapper contractMapper;
    private final CompanyRepository companyRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ContractorRepository contractorRepository;
    private final LegalDetailRepository legalDetailRepository;

    @Override
    public List<ContractDto> getAll() {
        return contractRepository.getAll().stream()
                .map(contractMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContractDto> getAll(String searchContr) {
        if ("null".equals(searchContr) || searchContr.isEmpty()) {
            List<Contract> all = contractRepository.findAll();
            return all.stream().map(contractMapper::toDto).collect(Collectors.toList());
        } else {
            List<Contract> list = contractRepository.search(searchContr);
            return list.stream().map(contractMapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<ContractDto> search(Specification<Contract> specification) {
        return executeSearch(contractRepository, contractMapper::toDto, specification);
    }

    @Override
    public ContractDto getById(Long id) {
        return contractMapper.toDto(contractRepository.getOne(id));
    }

    @Override
    public ContractDto create(ContractDto contractDto) {
        return saveOrUpdate(contractDto);
    }

    @Override
    public ContractDto update(ContractDto contractDto) {
        return saveOrUpdate(contractDto);
    }

    private ContractDto saveOrUpdate(ContractDto contractDto) {
        Contract contract = contractMapper.toModel(contractDto);

        contract.setCompany(companyRepository.findById(contractDto.getCompanyId()).orElse(null));
        LocalDate date = LocalDate.parse(contractDto.getContractDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        contract.setContractDate(date);
        contract.setBankAccount(bankAccountRepository.findById(contractDto.getBankAccountId()).orElse(null));
        contract.setContractor(contractorRepository.findById(contractDto.getContractorId()).orElse(null));
        contract.setLegalDetail(legalDetailRepository.findById(contractDto.getLegalDetailId()).orElse(null));

        return contractMapper.toDto(contractRepository.save(contract));
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteAllByContractId(id);
        contractRepository.deleteById(id);
    }


}
