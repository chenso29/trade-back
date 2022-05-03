package src.main.java.com.trade_accounting.models.entity.warehouse;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.company.Contractor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipments")

public class Shipment extends OperationsAbstract {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<ShipmentProduct> shipmentProducts;

    @Column(name = "paid")
    private BigDecimal paid;

    @Column(name = "is_spend")
    @ColumnDefault("false")
    private Boolean isSpend;


}

