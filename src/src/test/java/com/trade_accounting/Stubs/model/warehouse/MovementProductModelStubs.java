package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.models.entity.warehouse.MovementProduct;

import java.math.BigDecimal;

import static com.trade_accounting.Stubs.ModelStubs.getProduct;

public class MovementProductModelStubs {
    public static MovementProduct getMovementProduct(Long id) {
        return new MovementProduct(
                id,
                getProduct(id),
                BigDecimal.ONE,
                BigDecimal.ONE
        );
    }
}
