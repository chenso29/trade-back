package src.test.java.com.trade_accounting.Stubs.model.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseCurrentBalance;

public class PurchaseCurrentBalanceStubs {
    public static PurchaseCurrentBalance getPurchaseCurrentBalance(Long id) {
        return PurchaseCurrentBalance.builder()
                .id(id)
                .restOfTheWarehouse(22255L)
                .productsReserve(2000L)
                .productsAwaiting(50000L)
                .productsAvailableForOrder(18000L)
                .daysStoreOnTheWarehouse(236L)
                .build();
    }
}
