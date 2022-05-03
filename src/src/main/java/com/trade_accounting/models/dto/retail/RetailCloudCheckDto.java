package src.main.java.com.trade_accounting.models.dto.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetailCloudCheckDto {

    private Long id;

    private String date;

    private Long initiatorId;

    private Long fiscalizationPointId;

    private String status;

    private String cheskStatus;

    private BigDecimal total;

    private Long currencyId;

    private Long cashierId;
}
