package src.main.java.com.trade_accounting.models.entity.warehouse;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.util.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acceptances")
public class Acceptance extends OperationsAbstract {


    @Column(name = "incoming_number")
    private String incomingNumber;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @Column(name = "is_spend")
    @ColumnDefault("false")
    Boolean isSpend = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employeeChanged;

    @Column(name = "when_changed_date")
    private LocalDate whenChangedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;


    @OneToMany(mappedBy = "acceptance", fetch = FetchType.LAZY)
    private List<AcceptanceProduction> acceptanceProduction;
}
