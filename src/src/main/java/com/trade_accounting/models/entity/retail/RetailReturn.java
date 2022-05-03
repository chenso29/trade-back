package src.main.java.com.trade_accounting.models.entity.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "retail_returns")
@Builder
public class RetailReturn {

    @Id
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private RetailStore retailStore;

    @Column(name = "cash_amount")
    private BigDecimal cashAmount;

    @Column(name = "cashless_amount")
    private BigDecimal cashlessAmount;

//    @Column(name = "total_amount")
//    private BigDecimal totalAmount;

    @Column(name = "is_spend")
    @ColumnDefault("false")
    private Boolean isSpend;

    @Column(name = "is_send")
    @ColumnDefault("false")
    private Boolean isSend;

    @Column(name = "is_print")
    @ColumnDefault("false")
    private Boolean isPrint;

    @Column(name = "comment")
    private String comment;
}