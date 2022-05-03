package src.test.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.dto.finance.MoneySubProfitLossDto;
import com.trade_accounting.models.dto.finance.PaymentDto;
import com.trade_accounting.repositories.warehouse.AcceptanceProductionRepository;
import com.trade_accounting.repositories.warehouse.ShipmentProductRepository;
import com.trade_accounting.services.interfaces.finance.LossProductService;
import com.trade_accounting.services.interfaces.finance.PaymentService;
import com.trade_accounting.services.interfaces.retail.RetailReturnService;
import com.trade_accounting.services.interfaces.retail.RetailSalesService;
import com.trade_accounting.services.interfaces.warehouse.AcceptanceService;
import com.trade_accounting.services.interfaces.warehouse.ShipmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class MoneySubProfitLossServiceImplTest {
    @Mock
    private PaymentService paymentService;
    @Mock
    private LossProductService lossProductService;
    @Mock
    private ShipmentService shipmentService;
    @Mock
    private ShipmentProductRepository shipmentProductRepository;
    @Mock
    private RetailReturnService retailReturnService;
    @Mock
    private RetailSalesService retailSalesService;
    @Mock
    private AcceptanceService acceptanceService;
    @Mock
    private AcceptanceProductionRepository acceptanceProductionRepository;
    @InjectMocks
    private MoneySubProfitLossServiceImpl moneySubProfitLossService;

    @Test
    void getMoneySubProfitLossDto() {
        MoneySubProfitLossDto mspl = moneySubProfitLossService.getMoneySubProfitLossDto();
        assertThat(mspl.getGrossProfit()).isNotNull();
        assertThat(mspl.getCostPrice()).isNotNull();
        assertThat(mspl.getNetProfit()).isNotNull();
        assertThat(mspl.getMarketing()).isNotNull();
        assertThat(mspl.getOperatingProfit()).isNotNull();
        assertThat(mspl.getOperatingExpenses()).isNotNull();
        assertThat(mspl.getRental()).isNotNull();
        assertThat(mspl.getRevenue()).isNotNull();
        assertThat(mspl.getSalary()).isNotNull();
        assertThat(mspl.getTaxesAndFees()).isNotNull();
        assertThat(mspl.getWriteOffs()).isNotNull();
    }

    @Test
    void getPaymentSumByParam(){
        BigDecimal bd = paymentService.getAll().stream()
                .filter(x -> x.getTypeOfPayment().equals("OUTGOING"))
                .filter(q -> q.getExpenseItem().equals("param"))
                .map(PaymentDto::getSum)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        assertThat(bd).isNotNull();
    }
}