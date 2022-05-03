package src.main.java.com.trade_accounting.models.entity.production;

import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "technical_operations")
public class TechnicalOperations extends OperationsAbstract {

    @Column
    private Integer volume;

    @OneToOne(fetch = FetchType.LAZY)
    private TechnicalCard technicalCard;

    @OneToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;


}
