package src.main.java.com.trade_accounting.models.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmployeeDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String sortNumber;

    private String phone;

    @Pattern(regexp = "[0-9]{12}")
    private String inn;

    private String description;

    private String email;

    private String password;

    private Long departmentDtoId;

    private Long positionDtoId;

    private Set<Long> roleDtoIds;

    private Long imageDtoId;
}