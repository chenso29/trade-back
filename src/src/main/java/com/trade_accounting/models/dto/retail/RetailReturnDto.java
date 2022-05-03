package src.main.java.com.trade_accounting.models.dto.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetailReturnDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long retailStoreId;

    private BigDecimal cashAmount;

    private BigDecimal cashlessAmount;

    private BigDecimal totalAmount;

    private Boolean isSpend;

    private Boolean isSend;

    private Boolean isPrint;

    private String comment;

    public RetailReturnDto(Long id, LocalDateTime date, Long retailStoreId, BigDecimal cashAmount, BigDecimal cashlessAmount,
                           BigDecimal totalAmount, Boolean isSpend, Boolean isSend, Boolean isPrint, String comment) {
        this.id = id;
        this.date = date.toString();
        this.retailStoreId = retailStoreId;
        this.cashAmount = cashAmount;
        this.cashlessAmount = cashlessAmount;
        this.totalAmount = totalAmount;
        this.isSpend = isSpend;
        this.isSend = isSend;
        this.isPrint = isPrint;
        this.comment = comment;
    }
}
