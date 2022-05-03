package src.main.java.com.trade_accounting.models.dto.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetailSalesDto {

    private Long id;

    @NotNull
    private String time;

    @NotNull
    private Long retailStoreId;

    @NotNull
    private Long contractorId;

    @NotNull
    private Long companyId;

    private BigDecimal sumCash;

    private BigDecimal sumNonСash;

    private BigDecimal prepayment;

    private BigDecimal sumDiscount;

    private BigDecimal sum;

    private boolean sent;

    private  boolean printed;

    private  String comment;
}
