package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.Stubs.model.company.TypeOfPriceModelStubs;
import com.trade_accounting.models.entity.warehouse.ProductPrice;

import java.math.BigDecimal;

public class ProductPriceModelStubs {
    public static ProductPrice getProductPrice(Long id) {
        return new ProductPrice(id, TypeOfPriceModelStubs.getTypeOfPrice(id), new BigDecimal(1));
    }
}
