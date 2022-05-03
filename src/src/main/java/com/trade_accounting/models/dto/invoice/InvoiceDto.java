package src.main.java.com.trade_accounting.models.dto.invoice;

import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDto {

    private Long id;
    private String comment;
    @NotNull
    private String date;
    @NotNull
    private String typeOfInvoice;
    @NotNull
    private Long companyId;
    @NotNull
    private Long contractorId;
    @NotNull
    private Long warehouseId;
    @NotNull
    private Long invoicesStatusId;

    private List<Long> invoiceProductsIds;
    private Boolean isSpend;
    private Boolean isSent;
    private Boolean isPrint;

    private Boolean isRecyclebin;

    public InvoiceDto(Long id, String date, String typeOfInvoice, Long companyId, Long contractorId, Long warehouseId,
                      boolean isSpend, boolean isSent, boolean isPrint, String comment, Long invoicesStatusId) {
        this.id = id;
        this.date = date;
        this.typeOfInvoice = typeOfInvoice;
        this.companyId = companyId;
        this.contractorId = contractorId;
        this.warehouseId = warehouseId;
        this.isSpend = isSpend;
        this.isSent = isSent;
        this.isPrint = isPrint;
        this.comment = comment;
        this.invoicesStatusId = invoicesStatusId;
    }

    public InvoiceDto(Long id,
                      LocalDateTime date,
                      TypeOfInvoice typeOfInvoice,
                      Long companyId,
                      Long contractorId,
                      Long warehouseId,
                      Boolean isSpend,
                      Boolean isSent,
                      Boolean isPrint,
                      String comment,
                      Long invoicesStatusId) {
        this.id = id;
        this.date = date.toString();
        this.typeOfInvoice = typeOfInvoice.toString();
        this.companyId = companyId;
        this.contractorId = contractorId;
        this.warehouseId = warehouseId;
        this.isSpend = isSpend;
        this.isSent = isSent;
        this.isPrint = isPrint;
        this.comment = comment;
        this.invoicesStatusId = invoicesStatusId;
    }
}
