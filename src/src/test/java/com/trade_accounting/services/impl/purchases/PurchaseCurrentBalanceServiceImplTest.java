package src.test.java.com.trade_accounting.services.impl.purchases;

import com.trade_accounting.Stubs.dto.purchases.PurchaseCurrentBalanceDtoStubs;
import com.trade_accounting.Stubs.model.purchases.PurchaseCurrentBalanceStubs;
import com.trade_accounting.models.dto.purchases.PurchaseCurrentBalanceDto;
import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;
import com.trade_accounting.repositories.purchases.PurchaseCurrentBalanceRepository;
import com.trade_accounting.utils.mapper.purchases.PurchaseCurrentBalanceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseCurrentBalanceServiceImplTest {

    @InjectMocks
    private PurchaseCurrentBalanceServiceImpl purchaseCurrentBalanceService;

    @Mock
    private PurchaseCurrentBalanceRepository purchaseCurrentBalanceRepository;

    @Spy
    private PurchaseCurrentBalanceMapper purchaseCurrentBalanceMapper;

    @Test
    void getAll() {
        when(purchaseCurrentBalanceRepository.findAll())
                .thenReturn(
                        List.of(
                                PurchaseCurrentBalanceStubs.getPurchaseCurrentBalance(1L),
                                PurchaseCurrentBalanceStubs.getPurchaseCurrentBalance(2L),
                                PurchaseCurrentBalanceStubs.getPurchaseCurrentBalance(3L)
                        )
                );
        List<PurchaseCurrentBalanceDto> purchaseControlDtos = purchaseCurrentBalanceService.getAll();

        assertEquals(3, purchaseControlDtos.size());
    }

    @Test
    void getById() {
        when(purchaseCurrentBalanceRepository.getOne(anyLong()))
                .thenReturn(PurchaseCurrentBalanceStubs.getPurchaseCurrentBalance(1L));

        PurchaseCurrentBalanceDto purchaseCurrentBalance = purchaseCurrentBalanceService.getById(1L);

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
        purchaseCurrentBalanceService.deleteById(anyLong());
        verify(purchaseCurrentBalanceRepository).deleteById(anyLong());
    }

    public void saveOrUpdate() {
        when(purchaseCurrentBalanceRepository.save(any(PurchaseCurrentBalance.class)))
                .thenReturn(PurchaseCurrentBalanceStubs.getPurchaseCurrentBalance(1L));

        PurchaseCurrentBalanceDto purchaseCurrentBalanceDto = purchaseCurrentBalanceService
                .create(PurchaseCurrentBalanceDtoStubs.getDto(1L));

        assertEquals(1, purchaseCurrentBalanceDto.getId());
        verify(purchaseCurrentBalanceRepository).save(any(PurchaseCurrentBalance.class));
    }
}