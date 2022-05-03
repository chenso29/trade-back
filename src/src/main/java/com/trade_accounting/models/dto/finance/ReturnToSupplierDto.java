package src.main.java.com.trade_accounting.models.dto.finance;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnToSupplierDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long companyId;

    @NotNull
    private Long contractorId;

    @NotNull
    private Long contractId;

    private Boolean isSend;

    private Boolean isPrint;

    private String comment;

}
