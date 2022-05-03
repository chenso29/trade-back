package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovementProductDto {

    private Long id;

    private Long productId;

    private BigDecimal amount;

    private BigDecimal price;
}
