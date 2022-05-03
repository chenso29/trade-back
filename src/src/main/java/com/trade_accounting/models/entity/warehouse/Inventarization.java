package src.main.java.com.trade_accounting.models.entity.warehouse;


import com.trade_accounting.models.entity.util.OperationsAbstract;
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
import java.util.List;


@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventarizations")
public class Inventarization extends OperationsAbstract {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @Column(name = "status")
    @ColumnDefault("false")
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY)
    private List<InventarizationProduct> inventarizationProducts;
}
