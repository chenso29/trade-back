package src.test.java.com.trade_accounting.Stubs.model.invoice;

import com.trade_accounting.models.entity.invoice.InternalOrderProduct;
import com.trade_accounting.Stubs.ModelStubs;

import java.math.BigDecimal;

public class InternalOrderProductModelStubs {
    public static InternalOrderProduct getInternalOrderProduct(Long id) {
        return InternalOrderProduct.builder()
                .id(id)
                .product(ModelStubs.getProduct(id))
                .amount(BigDecimal.ONE)
                .price(BigDecimal.ONE)
                .build();
    }
}
