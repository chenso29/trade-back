package src.main.java.com.trade_accounting.models.dto.finance;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayoutDto {

    Long id;

    String date;

    Long retailStoreId;

    String whoWasPaid;

    Long companyId;

    Boolean isSent;

    Boolean isPrint;

    String comment;
}
