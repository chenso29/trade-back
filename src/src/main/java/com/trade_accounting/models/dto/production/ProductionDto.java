package src.main.java.com.trade_accounting.models.dto.production;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionDto {

    private Long id;

    private Long technicalCardId;

    private Long requestsProductionsId;
}
