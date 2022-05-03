package src.test.java.com.trade_accounting.Stubs.model.retail;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.retail.RetailShift;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RetailShiftModelStubs {

    public static RetailShift getRetailShiftModelStubs (Long id) {
        return RetailShift.builder()
                .id(id)
                .dataOpen(LocalDateTime.now())
                .dataClose(LocalDateTime.now())
                .retailStore(ModelStubs.getRetailStore(1L))
                .warehouse(ModelStubs.getWarehouse(1L))
                .company(ModelStubs.getCompany(1L))
                .bank("alfa")
                .revenuePerShift(new BigDecimal(1000))
                .received(new BigDecimal(1000))
                .amountOfDiscounts(new BigDecimal(1000))
                .commission_amount(new BigDecimal(1000))
                .sent(false)
                .printed(false)
                .comment("j")
                .build();
    }
}