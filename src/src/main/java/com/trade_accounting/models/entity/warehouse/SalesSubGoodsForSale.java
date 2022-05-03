package src.main.java.com.trade_accounting.models.entity.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales_sub_goods_for_sale")
public class SalesSubGoodsForSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name")
    private Product name;

    @Column(name = "code")
    private Long code;

    @Column(name = "vendor_code")
    private Long vendorCode;

    @Column(name = "transferred")
    private Integer transferred;

    @Column(name = "accepted")
    private Integer accepted;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "returned")
    private Long returned;

    @Column(name = "remainder")
    private BigDecimal remainder;

    @Column(name = "report_amount")
    private Long reportAmount;

    @Column(name = "report_sum")
    private BigDecimal reportSum;

    @Column(name = "not_report_amount")
    private Long notReportAmount;

    @Column(name = "not_report_sum")
    private BigDecimal notReportSum;
}
