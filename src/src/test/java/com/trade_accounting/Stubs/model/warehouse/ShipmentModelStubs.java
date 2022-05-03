package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.models.entity.warehouse.Shipment;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.trade_accounting.Stubs.ModelStubs.getContractor;
import static com.trade_accounting.Stubs.ModelStubs.getWarehouse;

public class ShipmentModelStubs {
    public static Shipment getShipment(Long id){
        return Shipment.builder()
                .contractor(getContractor(id))
                .warehouse(getWarehouse(id))
                .paid(new BigDecimal(198))
                .shipmentProducts(new ArrayList<>())
                .build();
    }
}
