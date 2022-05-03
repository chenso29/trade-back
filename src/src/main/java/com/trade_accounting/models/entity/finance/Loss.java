package src.main.java.com.trade_accounting.models.entity.finance;


import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loss")
public class Loss extends OperationsAbstract {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @OneToMany(fetch = FetchType.LAZY)
    private List<LossProduct> lossProducts;
}