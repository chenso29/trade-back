package src.test.java.com.trade_accounting.Stubs.model.util;

import com.trade_accounting.models.entity.util.BonusProgram;

import java.math.BigDecimal;
import java.util.List;

import static com.trade_accounting.Stubs.ModelStubs.getContractorGroup;

public class BonusProgramModelStubs {

    public static BonusProgram getBonusProgram(Long id) {
        return BonusProgram.builder()
                .id(id)
                .name("Бонусная программа 1")
                .activeStatus(true)
                .allContractors(false)
                .contractorGroups(List.of(getContractorGroup(1L)))
                .accrualRule(new BigDecimal(1.5))
                .writeOffRules(new BigDecimal(1.5))
                .maxPaymentPercentage((short)25)
                .numberOfDays((short)10)
                .welcomePoints(true)
                .numberOfPoints(100L)
                .registrationInBonusProgram(true)
                .firstPurchase(false)
                .build();
    }
}
