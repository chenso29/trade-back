package src.test.java.com.trade_accounting.Stubs.model.warehouse;

import com.trade_accounting.models.entity.warehouse.Acceptance;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.trade_accounting.Stubs.ModelStubs.*;

public class AcceptanceModelStubs {
    public static Acceptance getAcceptance(Long id) {
        return Acceptance.builder()
                .id(id)
                .acceptanceProduction(new ArrayList<>())
                .contract(getContract(1L))
                .contractor(getContractor(1L))
                .comment("Комментарий " + id)
                .incomingNumber("100")
                .date(LocalDateTime.now())
                .warehouse(getWarehouse(1L))
                .build();
    }
}
