package src.main.java.com.trade_accounting.models.dto.production;

import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StagesProductionDto {

    private Long id;

    private String name;

    private String description;

    private Long departmentId;

    private Long employeeId;

    public void setDepartment(Department department) {
    }

    public void setEmployee(Employee employee) {
    }
}