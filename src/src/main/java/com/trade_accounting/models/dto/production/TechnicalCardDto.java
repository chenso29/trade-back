package src.main.java.com.trade_accounting.models.dto.production;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalCardDto {

    private Long id;

    private String name;

    private String comment;

    private String productionCost;

    private Long technicalCardGroupId;

    private List<Long> finalProductionId;

    private List<Long> materialsId;
}
