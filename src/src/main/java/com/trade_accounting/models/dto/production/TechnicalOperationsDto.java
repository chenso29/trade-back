package src.main.java.com.trade_accounting.models.dto.production;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalOperationsDto {

    private Long id;

    private String comment;

    private Boolean isPrint = false;

    private Boolean isSent = false;

    private Integer volume;

    private String date;

    private Long technicalCard;

    private Long warehouse;

    private Long companyId;

    private Boolean isRecyclebin;
}
