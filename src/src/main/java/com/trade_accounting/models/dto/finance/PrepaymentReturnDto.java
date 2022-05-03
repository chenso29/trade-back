package src.main.java.com.trade_accounting.models.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrepaymentReturnDto {

    private Long id;

    private String time;

    private Long retailStoreId;

    private Long contractorId;

    private Long companyId;

    private BigDecimal sumCash;

    private BigDecimal sumNonСash;

    private boolean sent;

    private boolean printed;

    private String comment;

}
