package src.main.java.com.trade_accounting.models.dto.production;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestsProductionsDto {

    private Long id;

    private String numberOfTheCertificate;

    @NotNull
    private String dateOfTheCertificate;

    @NotNull
    private Long technicalCardId;

    private Integer volume;

    @NotNull
    private Long warehouseId;
}
