package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptanceDto {

    private Long id;

    private String incomingNumber;

    private String comment;

    private String date;

    private String whenChangedDate;

    private Long contractorId;

    private Long warehouseId;

    private Long contractId;

    private Long companyId;

    private Long employeeChangedId;

    private Long projectId;

    Boolean isSent;

    Boolean isPrint;

    Boolean isSpend;

    private List<AcceptanceProductionDto> acceptanceProduction;

    private List<Long> acceptanceProductIds;

    private Boolean isRecyclebin;
}
