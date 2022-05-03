package src.main.java.com.trade_accounting.models.entity.warehouse;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.util.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movement")
public class Movement extends OperationsAbstract {


    @Column(name = "when_changed_date")
    private LocalDate whenСhangedDate;

    //Со склада
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

// На склад
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouseTo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employeeChanged;

    @Column(name = "is_spend")
    @ColumnDefault("false")
    private Boolean isSpend = false;

    @OneToMany(fetch = FetchType.LAZY)
    private List<MovementProduct> movementProducts;
}
