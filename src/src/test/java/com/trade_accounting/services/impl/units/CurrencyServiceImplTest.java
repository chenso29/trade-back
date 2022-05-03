package src.test.java.com.trade_accounting.services.impl.units;

import com.trade_accounting.models.entity.units.Currency;
import com.trade_accounting.models.dto.units.CurrencyDto;
import com.trade_accounting.repositories.units.CurrencyRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.units.CurrencyDtoStubs;
import com.trade_accounting.utils.mapper.units.CurrencyMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Spy
    private CurrencyMapperImpl currencyMapper;
    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Test
    void getAll_shouldReturnListFilledCurrencyDto() {
        when(currencyRepository.getAll())
                .thenReturn(
                        List.of(
                                CurrencyDtoStubs.getCurrencyDto(1L),
                                CurrencyDtoStubs.getCurrencyDto(2L),
                                CurrencyDtoStubs.getCurrencyDto(3L)
                        )
                );
        List<CurrencyDto> currencyDtoList = currencyService.getAll();
        assertNotNull(
                currencyDtoList,
                "failure - expected that a list of currencyDto not null"
        );
        assertTrue(
                currencyDtoList.size() > 0,
                "failure - expected that a list of currencyDto grater than 0"
        );
        for (CurrencyDto currencyDto : currencyDtoList) {
            currencyDtoIsCorrectlyInited(currencyDto);
        }
    }

    @Test
    void getAll_shouldReturnEmptyListCurrencyDto() {
        when(currencyRepository.getAll())
                .thenReturn(new ArrayList<>());

        List<CurrencyDto> currencys = currencyService.getAll();

        assertNotNull(currencys, "Failure - expected that list of currency not null");
        assertEquals(0, currencys.size(), "failure - expected that size of list of currency equals 0");
    }

    @Test
    void search_shouldReturnListFilledCurrencyDto() {
        when(currencyRepository.findAll(Mockito.<Specification<Currency>>any()))
                .thenReturn(
                        List.of(
                                ModelStubs.getCurrency(1L),
                                ModelStubs.getCurrency(2L),
                                ModelStubs.getCurrency(3L)
                        )
                );

        List<CurrencyDto> currencys = currencyService
                .search(SpecificationStubs.getCurrencySpecificationStub());

        assertNotNull(currencys, "failure - expected that a list of currencyDto not null");
        assertTrue(currencys.size() > 0, "failure - expected that a list of currencyDto greater than 0");

        for (CurrencyDto currency : currencys) {
            currencyDtoIsCorrectlyInited(currency);
        }
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        currencyService.create(
                CurrencyDtoStubs.getCurrencyDto(1L)
        );

        verify(currencyRepository).save(any(Currency.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        currencyService.update(
                CurrencyDtoStubs.getCurrencyDto(1L)
        );

        verify(currencyRepository).save(any(Currency.class));
    }

    @Test
    void getById_shouldReturnFilledCurrencyDto() {
        CurrencyDto currencyFromRepo =
                CurrencyDtoStubs.getCurrencyDto(1L);

        when(currencyRepository.getById(anyLong()))
                .thenReturn(currencyFromRepo);

        CurrencyDto currencyDto = currencyService.getById(1L);

        currencyDtoIsCorrectlyInited(currencyDto);
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        currencyService.deleteById(1L);
        verify(currencyRepository).deleteById(anyLong());
    }

    private void currencyDtoIsCorrectlyInited(CurrencyDto currencyDto) {
        assertNotNull(currencyDto, "failure - fail in passed currencyDto");
        assertNotNull(currencyDto.getId(), "failure - fail in field 'id' into currencyDto");
        assertNotNull(currencyDto.getFullName(), "failure - fail in field 'fullName' into currencyDto");
    }
}