package src.main.java.com.trade_accounting.models.dto.retail;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetailOperationWithPointsDto {

    private Long id;

    @NotNull
    private Long number;

    @NotNull
    private String currentTime;

    private String typeOperation;

    @NotNull
    private Long numberOfPoints;

    private String accrualDate;

    @NotNull
    private Long bonusProgramId;

    @NotNull
    private Long contractorId;

    private Long taskId;

    private List<Long> fileIds;
}
