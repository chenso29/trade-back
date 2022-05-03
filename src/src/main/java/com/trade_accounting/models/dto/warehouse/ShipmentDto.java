package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentDto {

    private Long id;
    @NotNull
    private String date;
    @NotNull
    private Long warehouseId;
    @NotNull
    private Long companyId;
    @NotNull
    private Long contractorId;

    private List<Long> shipmentProductsIds;

    private BigDecimal sum;

    private BigDecimal paid;

    private Boolean isSpend;

    private Boolean isSend;

    private Boolean isPrint;

    private String comment;

    private Boolean isRecyclebin;

    public ShipmentDto(Long id, LocalDateTime date,
                       Long warehouseId, Long companyId,
                       Long contractorId, BigDecimal sum,
                       BigDecimal paid, Boolean isSpend,
                       Boolean isSend, Boolean isPrint,
                       String comment, List<Long> shipmentProductsIds) {
        this.id = id;
        this.date = date.toString();
        this.warehouseId = warehouseId;
        this.companyId = companyId;
        this.contractorId = contractorId;
        this.sum = sum;
        this.paid = paid;
        this.isSpend = isSpend;
        this.isSend = isSend;
        this.isPrint = isPrint;
        this.comment = comment;
        this.shipmentProductsIds = shipmentProductsIds;
    }
}
