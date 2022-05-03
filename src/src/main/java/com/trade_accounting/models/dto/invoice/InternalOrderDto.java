package src.main.java.com.trade_accounting.models.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Pavel Andrusov
 * @version 1.0.0
 * */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternalOrderDto {
    private Long id;

    private List<Long> internalOrderProductsIds;

    @NotNull
    private String date;

    @NotNull
    private Long companyId;

    @NotNull
    private Long warehouseId;

    private Boolean isSent = null;

    private Boolean isPrint = null;

    private String comment;

    private Boolean isRecyclebin;
}
