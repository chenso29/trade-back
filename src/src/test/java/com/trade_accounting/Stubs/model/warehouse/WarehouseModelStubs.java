package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.models.entity.warehouse.Warehouse;

/**
 * @author Stanislav Dusiak
 * @since 13.08.2021
 */

public class WarehouseModelStubs {
    public static Warehouse getWarehouse(Long id) {
        return Warehouse.builder()
                .id(id)
                .name("Основной склад")
                .sortNumber("00001")
                .address(null)
                .commentToAddress(null)
                .comment(null)
                .build();
    }
}
