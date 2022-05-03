package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.model.invoice.InvoiceProductModelStubs;
import com.trade_accounting.models.entity.warehouse.Revenue;

import java.math.BigDecimal;

public class RevenueModelStubs {
    public static Revenue getRevenue(Long id){
        return Revenue.builder()
                .id(id)
                .product(ModelStubs.getProduct(id))
                .description("Описание"+id)
                .itemNumber(new Integer(34))
                .amountAcceptance(new BigDecimal(198))
                .acceptance(AcceptanceModelStubs.getAcceptance(id))
                .invoiceProduct(InvoiceProductModelStubs.getInvoiceProduct(id))
                .startOfPeriodAmount(12)
                .startOfPeriodSumOfPrice(13)
                .endOfPeriodAmount(32)
                .endOfPeriodSumOfPrice(45)
                .comingAmount(2)
                .spendingAmount(55)
                .spendingSumOfPrice(45)
                .build();



    }
}
