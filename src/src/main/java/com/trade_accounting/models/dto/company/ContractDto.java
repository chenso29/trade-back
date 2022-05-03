package src.main.java.com.trade_accounting.models.dto.company;

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
public class ContractDto {

    private Long id;

    private String number;

    private String contractDate;

    @NotNull
    private Long companyId;

    private Long bankAccountId;

    @NotNull
    private Long contractorId;

    private BigDecimal amount;

    private Boolean archive;

    private String comment;

    private Long legalDetailId;
}
