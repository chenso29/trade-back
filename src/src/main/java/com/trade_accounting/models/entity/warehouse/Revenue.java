package src.main.java.com.trade_accounting.models.entity.warehouse;

import com.trade_accounting.models.entity.invoice.InvoiceProduct;
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
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "revenue")
public class Revenue {

    @Id
    @NotNull
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @NotNull
    private String description;

    @NotNull
    private Integer itemNumber;

    @NotNull
    private BigDecimal amountAcceptance;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Acceptance acceptance;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private AcceptanceProduction acceptanceProduction;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private InvoiceProduct invoiceProduct;

    @NotNull
    private Integer startOfPeriodAmount;

    @NotNull
    private Integer startOfPeriodSumOfPrice;

    @NotNull
    private Integer endOfPeriodAmount;

    @NotNull
    private Integer endOfPeriodSumOfPrice;

    @NotNull
    private Integer comingAmount;

    @NotNull
    private Integer comingSumOfPrice;

    @NotNull
    private Integer spendingAmount;

    @NotNull
    private Integer spendingSumOfPrice;
}
