package src.main.java.com.trade_accounting.models.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceReceivedDto {

    private Long id;

    private String comment;

    @NotNull
    private String data;

    @NotNull
    private Long companyId;

    @NotNull
    private Long contractorId;

    @NotNull
    private Long acceptanceId;

    private Long incomNumber;

    private String incomData;

    private Boolean isSpend;

    private Boolean isSend;

    private Boolean isPrint;

}
