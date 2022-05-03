package src.main.java.com.trade_accounting.models.entity.invoice;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Pavel Andrusov
 * @version 1.0.0
 */

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "internal_order")
public class InternalOrder extends OperationsAbstract {

    @OneToMany(fetch = FetchType.LAZY)
    private List<InternalOrderProduct> internalOrderProducts;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

}
