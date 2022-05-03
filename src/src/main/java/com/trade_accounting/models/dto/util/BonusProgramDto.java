package src.main.java.com.trade_accounting.models.dto.util;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ivanov Daniil
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonusProgramDto {

    Long id;

    @NotNull
    String name;

    Boolean activeStatus;

    Boolean allContractors;

    @OneToMany(fetch = FetchType.LAZY)
    List<Long> contractorGroupIds;

    BigDecimal accrualRule;

    BigDecimal writeOffRules;

    @Min(0)
    @Max(100)
    Short maxPaymentPercentage;

    Short numberOfDays;

    Boolean welcomePoints;

    Long numberOfPoints;

    Boolean registrationInBonusProgram;

    Boolean firstPurchase;
}
