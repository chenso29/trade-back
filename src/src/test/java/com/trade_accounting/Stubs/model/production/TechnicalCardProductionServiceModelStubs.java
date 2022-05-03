package src.test.java.com.trade_accounting.Stubs.model.production;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.production.TechnicalCardProduction;

public class TechnicalCardProductionServiceModelStubs {
    public static TechnicalCardProduction getTechnicalCardProduction(Long id){
        return TechnicalCardProduction.builder()
                .id(id)
                .amount(id)
                .product(ModelStubs.getProduct(id))
                .build();
    }
}
