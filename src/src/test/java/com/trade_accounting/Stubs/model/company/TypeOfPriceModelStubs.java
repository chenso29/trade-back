package src.test.java.com.trade_accounting.Stubs.model.company;

import com.trade_accounting.models.entity.company.TypeOfPrice;

public class TypeOfPriceModelStubs {
    public static TypeOfPrice getTypeOfPrice(Long id) {
        return new TypeOfPrice(id, "test", "test");
    }
}
