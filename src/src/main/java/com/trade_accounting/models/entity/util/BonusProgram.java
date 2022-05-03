package src.main.java.com.trade_accounting.models.entity.util;

import com.trade_accounting.models.entity.company.ContractorGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Entity
@Table(name = "bonus_program")
@Builder
public class BonusProgram{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    @NotNull
    String name;

    @Column(name = "active_status")
    Boolean activeStatus;

    // Все контрагенты
    @Column(name = "all_contractors")
    Boolean allContractors;

    //Контрагенты из групп
    @OneToMany(fetch = FetchType.LAZY)
    List<ContractorGroup> contractorGroups;

    // правило начилсения (...руб = 1 балл)
    @Column(name = "accrual_rule")
    BigDecimal accrualRule;

    // правило списания (...балл = 1 руб)
    @Column(name = "write_off_rules")
    BigDecimal writeOffRules;

    // Максимальный процент оплаты
    @Min(0)
    @Max(100)
    @Column(name = "max_payment_percentage")
    Short maxPaymentPercentage;

    // Баллы начисляются через
    @Column(name = "number_of_days")
    Short numberOfDays;

    // Приветственные баллы
    @Column(name = "welcome_points")
    Boolean welcomePoints;

    // колличество приветственных баллов
    @Column(name = "number_of_points")
    Long numberOfPoints;

    // после регистрации в бонусной программе
    @Column(name = "registration_in_bonus_program")
    Boolean registrationInBonusProgram;

    // после первой покупки
    @Column(name = "first_purchase")
    Boolean firstPurchase;

}
