package src.main.java.com.trade_accounting.models.dto.units;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitDto {

    private Long id;

    private String unitType;

    private String shortName;

    private String fullName;

    private String sortNumber;

    boolean generalAccess;

    private String departmentOwner;

    private String employeeOwner;

    private String dateOfChange;

    private String employeeChange;

    private Boolean isRecyclebin;

    public UnitDto(String type, String shortName, String fullName, String sortNumber, boolean generalAccess, String departmentOwner, String employeeOwner, String dateOfChange, String employeeChange) {
        this.unitType = type;
        this.shortName = shortName;
        this.fullName = fullName;
        this.sortNumber = sortNumber;
        this.generalAccess = generalAccess;
        this.departmentOwner = departmentOwner;
        this.employeeOwner = employeeOwner;
        this.dateOfChange = dateOfChange;
        this.employeeChange = employeeChange;
    }

    public UnitDto(Long id, String short_name, String full_name, String s) {

    }
}
