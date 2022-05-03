package src.main.java.com.trade_accounting.models.entity.finance;

import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.company.Contractor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "return_from_buyers")
public class ReturnFromBuyers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @ColumnDefault("false")
    private Boolean isSend;

    @ColumnDefault("false")
    private Boolean isPrint;

    private String comment;

    @ColumnDefault("false")
    private Boolean isConducted;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}