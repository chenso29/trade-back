package src.main.java.com.trade_accounting.models.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LossDto {

    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long companyId;

    private Boolean isSent = false;

    private Boolean isPrint = false;

    private String comment;

    private List<Long> lossProductsIds;

    private Boolean isRecyclebin;
}
