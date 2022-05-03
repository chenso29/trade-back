package src.main.java.com.trade_accounting.models.entity.company;



import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.entity.invoice.TypeOfInvoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier_accounts")
public class SupplierAccount extends OperationsAbstract {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @ColumnDefault("false")
    private Boolean isSpend;

    @NotNull
    @Column(name = "type_of_invoice")
    private TypeOfInvoice typeOfInvoice;

    @Column(name = "planned_date_payment")
    private LocalDateTime plannedDatePayment;
}
