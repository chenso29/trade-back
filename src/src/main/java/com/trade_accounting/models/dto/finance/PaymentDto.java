package src.main.java.com.trade_accounting.models.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private long id;

    private String typeOfPayment;

    private String typeOfDocument;

    private String paymentMethods;

    private String expenseItem;

    @NotNull
    private String number;

    private String time;

    private String date;

    @NotNull
    private long companyId;

    @NotNull
    private long contractorId;

    private long contractId;

    private long projectId;

    private BigDecimal sum;

    private boolean isConducted;

    private boolean isRecyclebin;
}
