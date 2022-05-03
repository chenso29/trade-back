package src.main.java.com.trade_accounting.models.dto.warehouse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceDto {
    private Long id;

    private Long typeOfPriceId;

    private BigDecimal value;
}
