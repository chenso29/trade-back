package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementDto {

    private Long id;

    @NotNull
    private String date;

    private String whenChangedDate;

    @NotNull
    private Long warehouseId;

    private BigDecimal sum;

    @NotNull
    private Long warehouseToId;

    @NotNull
    private Long companyId;

    private Long employeeChangedId;

    private Long projectId;

    private Boolean isSent = false;

    private Boolean isPrint = false;

    private Boolean isSpend = false;

    private String comment;

    private List<Long> movementProductsIds;

    private Boolean isRecyclebin;
}
