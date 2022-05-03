package src.main.java.com.trade_accounting.models.dto.warehouse;

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
public class BuyersReturnDto {

    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long contractorId;

    @NotNull
    private Long companyId;

    private BigDecimal sum;

    private Boolean isSent;

    private Boolean isPrint;

    private String comment;

    public BuyersReturnDto(Long id, LocalDateTime date,
                           Long warehouseId, Long contractorId,
                           Long companyId, BigDecimal sum, Boolean isSent,
                           Boolean isPrint, String comment) {
        this.id = id;
        this.date = date.toString();
        this.warehouseId = warehouseId;
        this.contractorId = contractorId;
        this.companyId = companyId;
        this.sum = sum;
        this.isSent = isSent;
        this.isPrint = isPrint;
        this.comment = comment;
    }
}
