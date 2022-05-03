package src.main.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.dto.company.BankAccountDto;
import com.trade_accounting.repositories.company.BankAccountRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.services.interfaces.company.BankAccountService;
import com.trade_accounting.utils.mapper.company.BankAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CompanyRepository companyRepository;

    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccountDto getBankByBic(String bic) {
        return bankAccountRepository.getBankByBic(bic).get(0);
    }

    @Override
    public List<String> getBankUniqueBic() {
        return bankAccountRepository.getListBankUniqueBic();
    }

    @Override
    public List<BankAccountDto> getAll() {
        return bankAccountRepository.findAll().stream()
                .map(bankAccountMapper::bankAccountToBankAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountDto getById(Long id) {
        return bankAccountMapper.bankAccountToBankAccountDto(
                bankAccountRepository.findById(id).orElse(new BankAccount())
        );
    }

    @Override
    public BankAccountDto create(BankAccountDto dto) {
        BankAccount bankAccount = bankAccountRepository.save(
                bankAccountMapper.bankAccountDtoToBankAccount(dto)
        );
        dto.setId(bankAccount.getId());
        return bankAccountMapper.bankAccountToBankAccountDto(bankAccount);
    }

    @Override
    public BankAccountDto update(BankAccountDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        BankAccount bankAccount = bankAccountRepository.getOne(id);
        Company company = companyRepository.getCompanyByBankAccounts(bankAccount);
        List<BankAccount> newBankAccountList = new ArrayList<>();
        for (BankAccount item : company.getBankAccounts()) {
            if (!item.equals(bankAccount)) {
                newBankAccountList.add(item);
            }
        }
        company.setBankAccounts(newBankAccountList);
        companyRepository.save(company);
        bankAccountRepository.deleteById(id);
    }
}
