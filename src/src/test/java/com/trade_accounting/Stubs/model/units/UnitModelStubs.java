package src.test.java.com.trade_accounting.Stubs.model.units;

import com.trade_accounting.models.entity.units.Unit;

public class UnitModelStubs {
    public static Unit getUnit(Long id){
        return Unit.builder()
                .id(id)
                .fullName("Full Name")
                .shortName("Short Name")
                .sortNumber(id.toString())
                .build();
    }
}
