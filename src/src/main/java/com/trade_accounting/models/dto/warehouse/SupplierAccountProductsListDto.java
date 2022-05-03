package src.main.java.com.trade_accounting.models.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierAccountProductsListDto {
    private Long id;

    private Long SupplierAccountId;

    private Long ProductId;

    private BigDecimal amount;

    private BigDecimal price;

    private BigDecimal sum;

    private String percentNds;

    private BigDecimal nds;

    private BigDecimal total;
}
