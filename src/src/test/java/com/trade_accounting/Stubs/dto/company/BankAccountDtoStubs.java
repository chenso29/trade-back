package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.models.dto.company.BankAccountDto;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.utils.mapper.company.BankAccountMapper;
import org.mapstruct.factory.Mappers;

public class BankAccountDtoStubs {
    private static final BankAccountMapper mapper = Mappers.getMapper(BankAccountMapper.class);
    public static BankAccountDto getBankAccountDto(Long id) {
        return mapper.bankAccountToBankAccountDto(
                ModelStubs.getBankAccount(id)
        );
    }
}
