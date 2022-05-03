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
public class RevenueDto {

    private Long id;

    private Integer itemNumber;

    private Long productId;                 // Product

    private String description;             // Product

    private Long unitId;                    // Product

    private String unitShortName;

    private Long acceptanceProductionId;    // AcceptanceProduction

    private Long amountAcceptance;          // AcceptanceProduction

    private Long acceptanceId;              // Acceptance

    private String incomingNumberDate;                     // Acceptance

    private Long invoiceProductId;          // InvoiceProduct

    private BigDecimal amountShipment;      // InvoiceProduct

    private Integer startOfPeriodAmount;

    private Integer startOfPeriodSumOfPrice;

    private Integer endOfPeriodAmount;

    private Integer endOfPeriodSumOfPrice;

    private Integer comingAmount;

    private Integer comingSumOfPrice;

    private Integer spendingAmount;

    private Integer spendingSumOfPrice;
}