package src.test.java.com.trade_accounting.Stubs.model.purchases;

import com.trade_accounting.models.entity.purchases.PurchaseForecast;

public class PurchaseForecastStubs {
    public static PurchaseForecast getPurchaseForecast(Long id) {
        return PurchaseForecast.builder()
                .id(id)
                .reservedDays(7L)
                .reservedProducts(1L)
                .ordered(true)
                .build();
    }
}
