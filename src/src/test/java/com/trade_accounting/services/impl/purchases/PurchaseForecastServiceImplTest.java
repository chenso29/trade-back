package src.test.java.com.trade_accounting.services.impl.purchases;

import com.trade_accounting.Stubs.dto.purchases.PurchaseCurrentBalanceDtoStubs;
import com.trade_accounting.Stubs.dto.purchases.PurchaseForecastDtoStubs;
import com.trade_accounting.Stubs.model.purchases.PurchaseCurrentBalanceStubs;
import com.trade_accounting.Stubs.model.purchases.PurchaseForecastStubs;
import com.trade_accounting.models.dto.purchases.PurchaseCurrentBalanceDto;
import com.trade_accounting.models.dto.purchases.PurchaseForecastDto;
import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;
import com.trade_accounting.models.entity.purchases.PurchaseForecast;
import com.trade_accounting.repositories.purchases.PurchaseForecastRepository;
import com.trade_accounting.utils.mapper.purchases.PurchaseForecastMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseForecastServiceImplTest {

    @InjectMocks
    private PurchaseForecastServiceImpl purchaseForecastService;

    @Mock
    private PurchaseForecastRepository purchaseForecastRepository;

    @Spy
    private PurchaseForecastMapper purchaseForecastMapper;

    @Test
    void getAll() {
        when(purchaseForecastRepository.findAll())
                .thenReturn(
                        List.of(
                                PurchaseForecastStubs.getPurchaseForecast(1L),
                                PurchaseForecastStubs.getPurchaseForecast(2L),
                                PurchaseForecastStubs.getPurchaseForecast(3L)
                        )
                );
        List<PurchaseForecastDto> purchaseControlDtos = purchaseForecastService.getAll();

        assertEquals(3, purchaseControlDtos.size());
    }

    @Test
    void getById() {
        when(purchaseForecastRepository.getOne(anyLong()))
                .thenReturn(PurchaseForecastStubs.getPurchaseForecast(1L));

        PurchaseForecastDto purchaseCurrentBalance = purchaseForecastService.getById(1L);

        assertEquals(1L, purchaseCurrentBalance.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        purchaseForecastService.deleteById(anyLong());
        verify(purchaseForecastRepository).deleteById(anyLong());
    }

    public void saveOrUpdate() {
        when(purchaseForecastRepository.save(any(PurchaseForecast.class)))
                .thenReturn(PurchaseForecastStubs.getPurchaseForecast(1L));

        PurchaseForecastDto purchaseForecastDto = purchaseForecastService
                .create(PurchaseForecastDtoStubs.getDto(1L));

        assertEquals(1, purchaseForecastDto.getId());
        verify(purchaseForecastRepository).save(any(PurchaseForecast.class));
    }
}