package src.test.java.com.trade_accounting.Stubs.model.retail;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.retail.RetailSales;

import java.math.BigDecimal;

public class RetailSalesModelStubs {

    public static RetailSales getRetailSales(Long id) {
        return  RetailSales.builder()
                .id(1L)
                .time("2021-08-11")
                .retailStore(ModelStubs.getRetailStore(1L))
                .contractor(ModelStubs.getContractor(1L))
                .company(ModelStubs.getCompany(1L))
                .sumCash(BigDecimal.valueOf(120000.00))
                .sumNonСash(BigDecimal.valueOf(0.00))
                .prepayment(BigDecimal.valueOf(0.00))
                .sumDiscount(BigDecimal.valueOf(0.00))
                .sum(BigDecimal.valueOf(120000.00))
                .sent(false)
                .printed(false)
                .comment("Комментарий тест 1")
                .build();
    }
}
