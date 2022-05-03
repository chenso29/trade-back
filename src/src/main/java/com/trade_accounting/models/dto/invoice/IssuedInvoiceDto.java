package src.main.java.com.trade_accounting.models.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssuedInvoiceDto {
    private Long id;
    private String comment;
    @NotNull
    private String date;
    @NotNull
    private Long companyId;
    @NotNull
    private Long contractorId;
    @NotNull
    private Long paymentId;
    private Boolean isSpend;
    private Boolean isSend;
    private Boolean isPrint;

    public IssuedInvoiceDto(Long id,
                      LocalDateTime date,
                      Long companyId,
                      Long contractorId,
                      Boolean isSpend,
                      String comment) {
        this.id = id;
        this.date = date.toString();
        this.companyId = companyId;
        this.contractorId = contractorId;
        this.isSpend = isSpend;
        this.comment = comment;
    }
}
