package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.models.entity.warehouse.Movement;

import java.time.LocalDate;
import java.util.List;

import static com.trade_accounting.Stubs.ModelStubs.*;
import static com.trade_accounting.Stubs.model.warehouse.MovementProductModelStubs.getMovementProduct;

public class MovementModelStubs {
    public static Movement getMovement(Long id) {
        return new Movement(
//                id,
//                LocalDateTime.now(),
                LocalDate.now(),
                getWarehouse(),
                getWarehouse(),
//                getCompany(id),
                getProject(id),
                getEmployee(id),
//                false, false,
                false,
//                "Комментарий 1",
                List.of(getMovementProduct(1L),
                        getMovementProduct(2L),
                        getMovementProduct(3L))
        );
    }
}
