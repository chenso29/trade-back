package src.main.java.com.trade_accounting.models.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MutualSettlementsDto {

    @NotNull
    private Long id;

    @NotNull
    private Long contractorId;

    @NotNull
    private Long employeeId;

    private Integer initialBalance;

    private Integer income;

    private Integer expenses;

    private Integer finalBalance;

}