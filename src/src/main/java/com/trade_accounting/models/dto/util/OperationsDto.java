package src.main.java.com.trade_accounting.models.dto.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OperationsDto {
    private Long id;

    private Long companyId;

    private String comment;

    private String date;

    private Boolean isSent = false;

    private Boolean isPrint = false;

    private Long warehouseId;

    private Boolean isRecyclebin;

}
