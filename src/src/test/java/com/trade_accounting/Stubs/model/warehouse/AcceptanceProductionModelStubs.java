package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;

import static com.trade_accounting.Stubs.ModelStubs.getProduct;

public class AcceptanceProductionModelStubs {
    public static AcceptanceProduction getAcceptanceProduction(Long id) {
        return AcceptanceProduction.builder()
                .id(id)
                .product(getProduct(1L))
                .amount(100L)
                .build();
    }
}
