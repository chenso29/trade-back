package src.main.java.com.trade_accounting.models.entity.finance;

import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prepayment_returns")
@Builder
public class PrepaymentReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String time;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private RetailStore retailStore;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Contractor contractor;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Company company;

    @Column(name = "sum_cash")
    private BigDecimal sumCash;

    @Column(name = "sum_non_cash")
    private BigDecimal sumNonСash;

    @Column(name = "sent")
    private boolean sent;

    @Column(name = "printed")
    private boolean printed;

    @Column(name = "comment")
    private String comment;

}
