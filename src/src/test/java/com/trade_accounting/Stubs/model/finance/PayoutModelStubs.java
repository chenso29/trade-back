package src.test.java.com.trade_accounting.Stubs.model.finance;

import com.trade_accounting.models.entity.finance.Payout;

import java.time.LocalDateTime;

import static com.trade_accounting.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.Stubs.model.retail.RetailStoreModelStubs.getRetailStore;

public class PayoutModelStubs {
    public static Payout getPayout(Long id) {
        return Payout.builder()
                .id(id)
                .date(LocalDateTime.parse(LocalDateTime.now().toString()))
                .retailStore(getRetailStore(id))
                .whoWasPaid("whoPaid")
                .company(getCompany(id))
                .isSent(true)
                .isPrint(true)
                .comment("comment")
                .build();
    }
}
