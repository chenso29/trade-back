package src.main.java.com.trade_accounting.models.dto.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailMakingDto {

    Long id;

    String date;

    Long retailStoreId;

    String fromWhom;

    Long companyId;

    BigDecimal sum;

    Boolean isPrint;

    Boolean isSent;

    String comment;
}