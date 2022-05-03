package src.main.java.com.trade_accounting.models.entity.finance;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс-модель корректировки
 *
 * @param id          - номер корректировки
 * @param date        - время корректировки
 * @param company     - компания
 * @param contractor  - контрагент
 * @param account     - счет
 * @param cashOffice  - касса
 * @param comment     - комментарий
 * @param dateChanged - когда изменен
 * @param whoChanged  - кто изменил

 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "balance_adjustments")
public class BalanceAdjustment {

    @Id
    @NotNull
    private Long id;

    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    private String account;

    private String cashOffice;

    private String comment;

    private String dateChanged;

    private String whoChanged;

}