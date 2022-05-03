package src.test.java.com.trade_accounting.Stubs.model.finance;

import com.trade_accounting.models.entity.finance.LossProduct;
import com.trade_accounting.Stubs.ModelStubs;

import java.math.BigDecimal;

public class LossProductModelStubs {
    public static LossProduct getLossProduct(Long id) {
        return LossProduct.builder()
                .id(id)
                .product(ModelStubs.getProduct(id))
                .amount(BigDecimal.ONE)
                .price(BigDecimal.ONE)
                .build();
    }
}
