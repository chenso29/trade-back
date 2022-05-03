package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.dto.finance.MoneySubCashFlowDto;
import com.trade_accounting.services.interfaces.finance.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MoneySubCashFlowServiceImplTest {
    @InjectMocks
    private MoneySubCashFlowServiceImpl moneySubCashFlowService;
    @Mock
    private PaymentService paymentService;

    @Test
    void getAll() {
        List<MoneySubCashFlowDto> moneySubCashFlowDtoList = moneySubCashFlowService.getAll();
        assertEquals(2, moneySubCashFlowDtoList.size());
    }

    @Test
    void filter() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        List<MoneySubCashFlowDto> moneySubCashFlowDto = moneySubCashFlowService.filter(start, end, 1L, 1L, 1L);
        assertEquals(2, moneySubCashFlowDto.size());
    }
}
