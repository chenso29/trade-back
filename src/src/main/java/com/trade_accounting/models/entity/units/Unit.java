package src.main.java.com.trade_accounting.models.entity.units;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "units")
@SuperBuilder
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_type")
    private String unitType;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "sort_number")
    private String sortNumber;

    @Column(name = "general_access")
    boolean generalAccess;

    @Column(name = "department_owner")
    private String departmentOwner;

    @Column(name = "employee_owner")
    private String employeeOwner;

    @Column(name = "date_of_change")
    private LocalDateTime dateOfChange;

    @Column(name = "employee_change")
    private String employeeChange;

    @Column(name = "is_recyclebin")
    @ColumnDefault("false")
    private Boolean isRecyclebin;

    public Unit(String shortName, String fullName, String sortNumber) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.sortNumber = sortNumber;
    }

    public Unit(Long id, String short_name, String full_name, String s) {
    }
}
