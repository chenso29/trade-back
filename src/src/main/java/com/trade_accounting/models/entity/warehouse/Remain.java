package src.main.java.com.trade_accounting.models.entity.warehouse;

import com.trade_accounting.models.entity.units.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "remains")
public class Remain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String vendorCode;

    private Integer balance;

    private Integer irreducibleBalance;

    private Integer reserve;

    private Integer expectation;

    private Integer available;

    @ManyToOne(fetch = FetchType.LAZY)
    private Unit unit;

    private Integer daysOnWarehouse;

    private Integer costPrice;

    private Integer sumOfCostPrice;

    private Integer salesCost;

    private Integer salesSum;
}
