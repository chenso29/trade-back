package src.main.java.com.trade_accounting.models.entity.finance;

import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contractor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prepayout")
public class Prepayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private RetailStore retailStore;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Column(name = "cash")
    private BigDecimal cash;

    @Column(name = "cashless")
    private BigDecimal cashless;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "is_sent")
    @ColumnDefault("false")
    private Boolean isSent;

    @Column(name = "is_print")
    @ColumnDefault("false")
    private Boolean isPrint;

    @Column(name = "comment")
    private String comment;
}
