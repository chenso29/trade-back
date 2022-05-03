package src.main.java.com.trade_accounting.models.dto.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetailStoreDto {

    private Long id;

    @NotNull
    private String name;

    private Boolean isActive;

    private String activityStatus;

    private BigDecimal revenue;

    @NotNull
    private Long companyId;

    private String salesInvoicePrefix;

    private String defaultTaxationSystem;

    private String orderTaxationSystem;

    private List<Long> cashiersIds;

}
