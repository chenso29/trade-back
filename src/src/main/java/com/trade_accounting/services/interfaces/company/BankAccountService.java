package src.main.java.com.trade_accounting.services.interfaces.company;

import com.trade_accounting.models.dto.company.BankAccountDto;
import com.trade_accounting.services.interfaces.util.AbstractService;

import java.util.List;

public interface BankAccountService extends AbstractService<BankAccountDto> {
    List<String> getBankUniqueBic();

    BankAccountDto getBankByBic(String bic);
}

