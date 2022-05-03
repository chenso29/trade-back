package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptanceProductionDto {

    private Long id;

    private Long amount;

    private Long productId;

    private Long acceptanceId;

    private BigDecimal price;
}
