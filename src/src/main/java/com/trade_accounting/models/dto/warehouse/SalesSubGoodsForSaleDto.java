package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalesSubGoodsForSaleDto {

    private Long id;

    @NotNull
    private Long productId;

    private Long code;

    private Long vendorCode;

    private Integer transferred;

    private Integer accepted;

    private Long amount;

    private BigDecimal sum;

    private Long returned;

    private BigDecimal remainder;

    private Long reportAmount;

    private BigDecimal reportSum;

    private Long notReportAmount;

    private BigDecimal notReportSum;
}
