package src.main.java.com.trade_accounting.models.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnAmountByProductDto {

    private Long id;

    private Long productId;

    private Long invoiceId;

    private Long contractorId;

    private BigDecimal amount;

}
