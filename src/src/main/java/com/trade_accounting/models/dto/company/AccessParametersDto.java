package src.main.java.com.trade_accounting.models.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessParametersDto {

    private Long id;

    private Boolean generalAccess;

    private Long employeeId;

    private Long departmentId;
}
