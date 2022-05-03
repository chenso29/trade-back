package src.main.java.com.trade_accounting.models.dto.retail;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetailShiftDto {

    private Long id;

    @NotNull
    private String dataOpen;

    private String dataClose;

    @NotNull
    private Long retailStoreId;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long companyId;

    private String bank;

    private BigDecimal revenuePerShift;

    private BigDecimal received;

    private BigDecimal amountOfDiscounts;

    private BigDecimal commission_amount;

    private Boolean sent;

    private Boolean printed;

    private String comment;
}
