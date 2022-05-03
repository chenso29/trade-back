package src.main.java.com.trade_accounting.models.entity.retail;

import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "retail_stores")
@Builder
public class RetailStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "is_active")
    @ColumnDefault("false")
    private Boolean isActive;

    @Column(name = "activity_status")
    private String activityStatus;

    @Column(name = "revenue")
    private BigDecimal revenue;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Company company;

    @Column(name = "sales_invoice_prefix")
    private String salesInvoicePrefix;

    @Column(name = "default_taxation_system")
    private String defaultTaxationSystem;

    @Column(name = "order_taxation_system")
    private String orderTaxationSystem;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Employee> cashiers;
}
