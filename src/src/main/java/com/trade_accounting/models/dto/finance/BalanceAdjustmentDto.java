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
public class BalanceAdjustmentDto {

    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    private Long companyId;

    @NotNull
    private Long contractorId;

    private String account;

    private String cashOffice;

    private String comment;

    private String dateChanged;

    private String whoChanged;

}
