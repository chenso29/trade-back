package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.dto.company.BankAccountDto;
import com.trade_accounting.repositories.company.BankAccountRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.company.BankAccountDtoStubs;
import com.trade_accounting.utils.mapper.company.BankAccountMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Spy
    private BankAccountMapperImpl bankAccountMapper;

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Test
    void getAll_shouldReturnListFilledBankAccountDto() {
        when(bankAccountRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getBankAccount(1L),
                                ModelStubs.getBankAccount(2L),
                                ModelStubs.getBankAccount(3L)
                        ).collect(Collectors.toList())
                );

        List<BankAccountDto> bankAccounts = bankAccountService.getAll();

        assertNotNull(bankAccounts, "failure - expected that a list of bankAccountDto not null");
        assertTrue(bankAccounts.size() > 0, "failure - expected that a list of bankAccountDto grater than 0");

        for (BankAccountDto bankAccount : bankAccounts) {
            bankAccountDtoIsCorrectlyInited(bankAccount);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListBankAccountDto() {
        when(bankAccountRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<BankAccountDto> bankAccounts = bankAccountService.getAll();

        assertNotNull(bankAccounts, "failure - expected that a list of bankAccountDto not null");
        assertEquals(0, bankAccounts.size(), "failure - expected that size of list of bankAccountDto equals 0");
    }

    @Test
    void getById_shouldReturnFilledBankAccountDto() {
        Optional<BankAccount> bankAccountFromRepo = Optional.of(
                ModelStubs.getBankAccount(1L)
        );

        when(bankAccountRepository.findById(anyLong()))
                .thenReturn(bankAccountFromRepo);

        BankAccountDto bankAccount = bankAccountService.getById(1L);

        assertNotNull(bankAccount, "failure - expected that bankAccount not null");
        bankAccountDtoIsCorrectlyInited(bankAccount);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        bankAccountService.create(
                BankAccountDtoStubs.getBankAccountDto(1L)
        );

        verify(bankAccountRepository).save(any(BankAccount.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        bankAccountService.update(
                BankAccountDtoStubs.getBankAccountDto(1L)
        );

        verify(bankAccountRepository).save(any(BankAccount.class));
    }

    @Test
    void deleteById() {
        bankAccountService.deleteById(1L);
        verify(bankAccountRepository).deleteById(1L);
    }

    void bankAccountDtoIsCorrectlyInited(BankAccountDto bankAccount) {
        //TODO Добавить not null полей
        assertNotNull(bankAccount, "Fail in passed employee");
        assertNotNull(bankAccount.getId(), "Fail in field 'id' of bankAccount");
    }
}