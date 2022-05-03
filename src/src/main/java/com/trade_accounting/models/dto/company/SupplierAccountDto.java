package src.main.java.com.trade_accounting.models.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierAccountDto {


    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long companyId;

    @NotNull
    private Long warehouseId;

    @NotNull
    private Long contractId;

    @NotNull
    private Long contractorId;

    @NotNull
    private String typeOfInvoice;

    private String plannedDatePayment;

    private Boolean isSpend;

    private String comment;

    private Boolean isRecyclebin;

    public SupplierAccountDto(Long id, String date, Long companyId,
                              Long warehouseId, Long contractId, Long contractorId,
                              String typeOfInvoice, String plannedDatePayment,
                              boolean isSpend, String comment) {
        this.id = id;
        this.date = date;
        this.companyId = companyId;
        this.warehouseId = warehouseId;
        this.contractId = contractId;
        this.contractorId = contractorId;
        this.typeOfInvoice = typeOfInvoice;
        this.plannedDatePayment = plannedDatePayment;
        this.isSpend = isSpend;
        this.comment = comment;
    }
}
