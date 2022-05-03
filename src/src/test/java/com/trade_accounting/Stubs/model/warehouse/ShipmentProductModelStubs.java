package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.entity.warehouse.ShipmentProduct;

import java.math.BigDecimal;

public class ShipmentProductModelStubs {
    public static ShipmentProduct getShipmentProduct(Long id){
        return ShipmentProduct.builder()
                .id(1L)
                .amount(new BigDecimal(17))
                .price(new BigDecimal(199))
                .product(ModelStubs.getProduct(2L))
                .build();
    }

}
