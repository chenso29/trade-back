package src.main.java.com.trade_accounting.models.entity.finance;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "corrections")
public class Correction {

    @Id
    @NotNull
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;


    @Column(name = "is_sent")
    @ColumnDefault("false")
    private Boolean isSent = false;

    @Column(name = "is_print")
    @ColumnDefault("false")
    private Boolean isPrint = false;

    @Column
    @ColumnDefault("false")
    private Boolean writeOffProduct = false;

    @Column(name = "comment")
    private String comment;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CorrectionProduct> correctionProducts;

    @Column(name = "is_recyclebin")
    @ColumnDefault("false")
    private Boolean isRecyclebin = false;
}
