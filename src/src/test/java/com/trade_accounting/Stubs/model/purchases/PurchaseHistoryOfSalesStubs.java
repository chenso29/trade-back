package src.test.java.com.trade_accounting.Stubs.model.purchases;


import com.trade_accounting.Stubs.model.warehouse.ProductPriceModelStubs;
import com.trade_accounting.models.entity.purchases.PurchaseHistoryOfSales;

import java.math.BigDecimal;

public class PurchaseHistoryOfSalesStubs {
    public static PurchaseHistoryOfSales getPurchaseHistoryOfSales(Long id) {

        return PurchaseHistoryOfSales.builder()
                .id(1L)
                .sumOfProducts(new BigDecimal(5))
                .productPrice(ProductPriceModelStubs.getProductPrice(id))
                .productMargin(new BigDecimal(5))
                .productProfitMargin(new BigDecimal(5))
                .productSalesPerDay(3L)
                .build();
    }

}
