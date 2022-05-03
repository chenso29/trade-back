package src.main.java.com.trade_accounting.models.entity.retail;

import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.entity.company.Company;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Сущность - Розница - Смены
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "retail_shifts")
@Builder
public class RetailShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_open")
    @NotNull
    private LocalDateTime dataOpen;

    @Column(name = "data_close")
    private LocalDateTime dataClose;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private RetailStore retailStore;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Warehouse warehouse;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Company company;

    @Column(name = "bank")
    @NotNull
    private String bank;

    //Выручка за смену
    @Column(name = "revenue_per_shift")
    private BigDecimal revenuePerShift;

    //Поступило
    @Column(name = "received")
    private BigDecimal received;

    //Сумма скидок
    @Column(name = "amount_of_discounts")
    private BigDecimal amountOfDiscounts;

    //Сумма комисcии
    @Column(name = "commission_amount")
    private BigDecimal commission_amount;

    @Column(name = "sent")
    private Boolean sent;

    @Column(name = "printed")
    private Boolean printed;

    @Column(name = "comment")
    private String comment;
}
