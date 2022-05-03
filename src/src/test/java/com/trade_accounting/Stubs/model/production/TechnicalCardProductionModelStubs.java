package src.test.java.com.trade_accounting.Stubs.model.production;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.production.TechnicalCardProduction;

public class TechnicalCardProductionModelStubs {
    public static TechnicalCardProduction getTechnicalCardProduction(Long id){
        return TechnicalCardProduction.builder()
                .id(id)
                .amount(1L)
                .product(ModelStubs.getProduct(1L))
                .build();

    }

}
