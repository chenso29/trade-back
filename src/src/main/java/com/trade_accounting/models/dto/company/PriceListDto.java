package src.main.java.com.trade_accounting.models.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceListDto {

    private Long id;

    @NotNull
    private String number;

    @NotNull
    private String time;

    @NotNull
    private Long companyId;

    private Boolean sent;

    private Boolean printed;

    private String commentary;
}
