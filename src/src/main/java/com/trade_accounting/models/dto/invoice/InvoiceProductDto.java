package src.main.java.com.trade_accounting.models.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceProductDto {

    private Long id;

    private Long invoiceId;

    private Long productId;

    private BigDecimal amount;

    private BigDecimal price;

}
