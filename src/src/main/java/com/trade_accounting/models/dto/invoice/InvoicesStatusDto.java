package src.main.java.com.trade_accounting.models.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoicesStatusDto {

    private Long id;

    private  String statusName;

}
