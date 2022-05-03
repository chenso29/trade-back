package src.main.java.com.trade_accounting.models.entity.retail;

import com.trade_accounting.models.entity.units.Currency;
import com.trade_accounting.models.entity.client.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "retail_cloud_checks")
public class RetailCloudCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private RetailStore initiator;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private RetailStore fiscalizationPoint;

    @Column(name = "status")
    private String status;

    @Column(name = "check_status")
    private String cheskStatus;

    @Column(name = "total")
    private BigDecimal total;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee cashier;
}
