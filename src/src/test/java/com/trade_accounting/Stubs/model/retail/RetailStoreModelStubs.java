package src.test.java.com.trade_accounting.Stubs.model.retail;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.retail.RetailStore;

import java.math.BigDecimal;
import java.util.List;

public class RetailStoreModelStubs {
    public static RetailStore getRetailStore(Long id) {
        return RetailStore.builder()
                .id(1L)
                .activityStatus("Был в сети вчера")
                .defaultTaxationSystem("ОСН")
                .isActive(true)
                .name("Ozon111")
                .orderTaxationSystem("УСН. Доход")
                .revenue(BigDecimal.valueOf(12000))
                .salesInvoicePrefix("SI")
                .company(ModelStubs.getCompany(1L))
                .cashiers(List.of(ModelStubs.getEmployee(id)))
                .build();
    }
}