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
public class ReturnFromBuyersDto {

    private Long id;

    private String date;

    private Long warehouseId;

    private Long contractorId;

    private Long companyId;

    private BigDecimal totalPrice;

    private Long contractId;

    private Boolean isSend;

    private Boolean isPrint;

    private String comment;

    private Boolean isConducted;
}
