package src.main.java.com.trade_accounting.models.entity.production;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//Заказы на производство
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders_of_production")
public class OrdersOfProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToOne(fetch = FetchType.LAZY)
    private TechnicalCard technicalCard;

    @Column
    private Integer volume;

    @Column
    private Integer produce;

    @Column
    private LocalDateTime plannedProductionDate;

    @Column
    @ColumnDefault("false")
    private Boolean isSent = false;

    @Column
    @ColumnDefault("false")
    private Boolean isPrint = false;

    @Column
    private String comment;

}
