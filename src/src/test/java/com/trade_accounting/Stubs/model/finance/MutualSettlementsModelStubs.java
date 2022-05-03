package src.test.java.com.trade_accounting.Stubs.model.finance;

import com.trade_accounting.models.entity.finance.MutualSettlements;

import static com.trade_accounting.Stubs.ModelStubs.getContractor;
import static com.trade_accounting.Stubs.ModelStubs.getEmployee;

public class MutualSettlementsModelStubs {
    public static MutualSettlements getMutualSettlements(Long id) {
        return MutualSettlements.builder()
                .id(id)
                .contractor(getContractor(1L))
                .employee(getEmployee(1L))
                .initialBalance(111)
                .income(111)
                .expenses(111)
                .finalBalance(111)
                .build();
    }
}
